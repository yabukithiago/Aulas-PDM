package com.examples.newnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
                                Text(text = "New News",
                                    style = MaterialTheme.typography.titleLarge)
                            },
                            navigationIcon = {
                                if (currentRoute != "home") {
                                    IconButton(onClick = { navController.navigate("home") }) { }
                                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                                }
                            }
                        )
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