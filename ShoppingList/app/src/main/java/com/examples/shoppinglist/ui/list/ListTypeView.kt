package com.examples.shoppinglist.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.examples.shoppinglist.ui.list.AddListViewModel
import com.examples.shoppinglist.ui.list.ShowListItemsViewModel
import com.examples.shoppinglist.ui.login.LoginViewModel
import com.examples.shoppinglist.ui.theme.ShoppingListTheme


@Composable
fun ListTypeView(navController: NavController, modifier: Modifier = Modifier){
    val viewModelShow = ShowListItemsViewModel()
    val viewModelLogin = LoginViewModel()


    Box(modifier = modifier.fillMaxSize() ){
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                navController.navigate("addListType")
            }) {
                Text(text = "Add new list")
            }

            Button(onClick = {
                navController.navigate("showLists")
            }) {
                Text(text = "Show lists")
            }

            Button(onClick = { viewModelLogin.logout(
                onLogoutSuccess = { navController.navigate("login") }) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Text("Logout")
            }
        }
    }

    LaunchedEffect (key1 = Unit){
        viewModelShow.loadListItems()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListTypeView() {
    ShoppingListTheme {
        ListTypeView(navController = rememberNavController())
    }
}