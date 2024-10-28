package com.examples.newnews.models

import org.json.JSONObject
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Date

fun String.encodeURL() : String{
    return  URLEncoder.encode(this, "UTF-8")
}

fun String?.toDate(): Date? {
    if (this.isNullOrEmpty()) return null // Retorna null se a string for nula ou vazia
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    return dateFormat.parse(this)
}

fun Date.toStringDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    return dateFormat.format(this)
}

data class Article (
    var id: Int,
    var titulo: String?,
    var descricao: String?,
    var url: String?,
    var image: String?,
    var publishedAt: Date?
) {
    companion object {
        fun fromJson(articleObject: JSONObject): Article {
            val id = articleObject.getInt("id")
            val title = articleObject.getString("titulo")
            val description = articleObject.getString("descricao")
            val url = articleObject.getString("url")
            val image = articleObject.getString("multimediaPrincipal")
            val publishedAt = articleObject.optString("publishedAt", null)?.toDate()
            return Article(id, title, description, url, image, publishedAt)
        }
    }
}