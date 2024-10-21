package com.examples.newnews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.examples.newnews.models.Article
import com.examples.newnews.ui.theme.NewNewsTheme

@Composable
fun HomeView( modifier: Modifier = Modifier){
    val articles = arrayListOf(
        Article(
            title = "Title 1",
            description = "Description 1",
            url = "https://www.google.com",
            urlToImage = "https://www.google.com",
            publishedAt = "2021-09-01"
        ),
        Article(
            title = "Title 2",
            description = "Description 2",
            url = "https://www.google.com",
            urlToImage = "https://www.google.com",
            publishedAt = "2021-09-02"
        ),
        Article(
            title = "Title 3",
            description = "Description 3",
            url = "https://www.google.com",
            urlToImage = "https://www.google.com",
            publishedAt = "2021-09-03"
        ),
        Article(
            title = "Title 4",
            description = "Description 4",
            url = "https://www.google.com",
            urlToImage = "https://www.google.com",
            publishedAt = "2021-09-04"
        ),
    )

    LazyColumn(modifier = modifier.fillMaxSize()) {
        itemsIndexed(
            items = articles
        ){ _, item ->
            ArticleView(article = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    NewNewsTheme {
        HomeView()
    }
}