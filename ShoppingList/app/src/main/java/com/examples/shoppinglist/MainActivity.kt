package com.examples.shoppinglist

import com.examples.shoppinglist.ui.item.ShowItemView
import com.examples.shoppinglist.ui.login.LoginView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.examples.shoppinglist.ui.list.EditListView
import com.examples.shoppinglist.ui.home.HomePage
import com.examples.shoppinglist.ui.item.AddNewItemView
import com.examples.shoppinglist.ui.list.AddListView
import com.examples.shoppinglist.ui.list.DeleteListView
import com.examples.shoppinglist.ui.list.ShareListView
import com.examples.shoppinglist.ui.list.ShowListsView
import com.examples.shoppinglist.ui.list.ShowUserView
import com.examples.shoppinglist.ui.register.RegisterView
import com.examples.shoppinglist.ui.theme.ShoppingListTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            ShoppingListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable("login") {
                            LoginView(navController, onLoginSuccess = { navController.navigate("home") })
                        }
                        composable("register"){
                            RegisterView(navController, onRegisterSuccess = { navController.navigate("login") })
                        }
                        composable("home") {
                            HomePage(navController)
                        }
                        composable("addList"){
                            AddListView(navController)
                        }
                        composable(
                            route = "editList/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            EditListView(navController, id)
                        }
                        composable("deleteList/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            DeleteListView(navController, id = id)
                        }
                        composable("shareList/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            ShareListView(navController, listId = id)
                        }
                        composable("addNewItem/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            AddNewItemView(navController, listId = id)
                        }
                        composable("showLists") {
                            ShowListsView(navController)
                        }
                        composable("showUsers"){
                            ShowUserView(navController)
                        }
                        composable(route = "showItems/{id}") { backStackEntry ->
                            val listId = backStackEntry.arguments?.getString("id") ?: ""
                            ShowItemView(navController, listId = listId)
                        }
                    }
                }

                LaunchedEffect(Unit) {

                    val auth = Firebase.auth

                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        navController.navigate("home")
                    }
                }
            }
        }
    }
}