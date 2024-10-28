package com.examples.newnews

import androidx.lifecycle.ViewModel
import com.examples.newnews.models.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

data class ArticleState(
    val articles: ArrayList<Article> = arrayListOf<Article>(),
    var isLoading: Boolean = false,
    var errorMessage: String = ""
)

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ArticleState())
    val uiState: StateFlow<ArticleState> = _uiState

    fun fetchArticles() {
        _uiState.value = ArticleState(
            isLoading = true
        )

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.publico.pt/api/list/ultimas")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                _uiState.value = ArticleState(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error"
                )
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val articleResult = arrayListOf<Article>()
                    val result = response.body!!.string()

                    try {
                        val jsonArray = JSONArray(result)
                        for (index in 0 until jsonArray.length()) {
                            val articleObject = jsonArray.getJSONObject(index)
                            val article = Article.fromJson(articleObject)
                            articleResult.add(article)
                        }
                        _uiState.value = ArticleState(
                            articles = articleResult,
                            isLoading = false,
                            errorMessage = ""
                        )
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        _uiState.value = ArticleState(
                            isLoading = false,
                            errorMessage = "Erro ao analisar os dados: ${e.message}"
                        )
                    }
                }
            }
        })
    }
}