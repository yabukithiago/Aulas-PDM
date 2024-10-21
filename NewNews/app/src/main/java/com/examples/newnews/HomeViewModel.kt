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

    fun fetchArticles(){
        _uiState.value = ArticleState(
            isLoading = true
        )

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?country=us&apiKey=cdb6e3e10371463d9e2916cc4090b1e7")
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

                    var articleResult = arrayListOf<Article>()
                    val result = response.body!!.string()

                    val jsonObject = JSONObject(result)
                    val status = jsonObject.getString("status")
                    if (status == "ok") {
                        val articlesArray = jsonObject.getJSONArray("articles")
                        for (index in 0 until articlesArray.length()) {
                            val articleObject = articlesArray.getJSONObject(index)
                            val article = Article.fromJson(articleObject)
                            articleResult.add(article)
                        }
                        _uiState.value = ArticleState(
                            articles = articleResult,
                            isLoading = false,
                            errorMessage = ""
                        )
                    }
                }
            }
        })
    }
}