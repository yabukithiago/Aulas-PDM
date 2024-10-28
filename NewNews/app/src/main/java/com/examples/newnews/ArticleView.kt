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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.examples.newnews.models.Article
import com.examples.newnews.models.toStringDate
import java.util.Date

@Composable
fun ArticleView(modifier: Modifier = Modifier, article: Article) {
    Row (modifier = modifier){
        article.image?.let {
            AsyncImage(
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp),
                model = it,
                contentDescription = "Article Image"
            )
        }?:run {
            Image(
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp),
                painter = painterResource(id = R.drawable.baseline_image_24),
                contentDescription = "Article Image"
            )
        }
        Column (modifier = Modifier.fillMaxWidth()){
            Text(text = article.titulo ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = article.descricao ?: "",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,)

            Text(modifier = Modifier.padding(top = 8.dp), text = article.publishedAt?.toStringDate() ?: "")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleRowViewPreview() {
    val article = Article(id = 0, titulo = "title", descricao = "description", url = "url", image = null, publishedAt = Date())
    ArticleView(article = article)
}