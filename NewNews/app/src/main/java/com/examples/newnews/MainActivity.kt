package com.examples.newnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.examples.newnews.ui.article.ArticleDetailView
import com.examples.newnews.ui.home.HomeView
import com.examples.newnews.ui.theme.NewNewsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewNewsTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                        TopAppBar(modifier = Modifier.height(56.dp),
                            title = {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                Text(text = "New News",
                                    style = MaterialTheme.typography.titleLarge,
                                    textAlign = TextAlign.Center)
                            }},
                            navigationIcon = {
                                if (currentRoute != "home") {
                                    IconButton(onClick = { navController.navigate("home") }) { }
                                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                                }
                            }
                        )
                    },
                    bottomBar = {
                            BottomAppBar(modifier = Modifier.fillMaxWidth().height(56.dp),
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    IconButton(onClick = { navController.navigate("home") }) {
                                        Icon(Icons.Filled.Home, contentDescription = "Home")
                                    }
                                    IconButton(onClick = { }) {
                                        Icon(Icons.Filled.Search, contentDescription = "Search")
                                    }
                                }
                            }
                    }) { innerPadding ->

                    NavHost(navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding))  {
                        composable( route = "home"){
                            HomeView(
                                modifier = Modifier.padding(innerPadding),
                                onArticleClick = {
                                    navController.navigate("article/${it}")
                                }
                            )
                        }
                        composable(route = "article/{url}"){
                            val url = it.arguments?.getString("url")
                            url?.let {
                                ArticleDetailView(url = it)
                            }
                        }
                    }

                }
            }
        }
    }
}