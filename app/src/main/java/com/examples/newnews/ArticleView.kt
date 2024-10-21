package com.examples.newnews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.examples.newnews.models.Article
import com.examples.newnews.ui.theme.NewNewsTheme

@Composable
fun ArticleView(article: Article) {
    Row(){
        Image(
            modifier = Modifier
                .width(128.dp)
                .height(128.dp),
            painter = painterResource(id = R.drawable.baseline_image_24),
            contentDescription = "Article Image")

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = article.title ?: "",
                style = MaterialTheme.typography.titleMedium)
            Text(text = article.description ?: "")
            Text(modifier = Modifier.padding(top = 8.dp), text = article.publishedAt ?: "")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleViewPreview() {
    NewNewsTheme {
        val article = Article(
            title = "Title 1",
            description = "Description 1",
            url = "https://www.google.com",
            urlToImage = "https://www.google.com",
            publishedAt = "2021-09-01"
        )
        ArticleView(article = article)
    }
}