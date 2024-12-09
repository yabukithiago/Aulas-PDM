package com.examples.shoppinglist.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.examples.shoppinglist.ui.cards.UserCard
import com.examples.shoppinglist.ui.components.TopBar

@Composable
fun ShowUserView(navController: NavController) {
    val viewModel: ShowUserViewModel = viewModel()
    val state by viewModel.state

    TopBar(title = "Share List", navController = navController)

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TopBar(title = "Users", navController = navController)
            LazyColumn(contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(
                    items = state.listUsers
                ) { _, item ->
                    UserCard (
                        navController = navController,
                        id = item.id,
                        name = item.nome,
                        onClick = {
                            navController.navigate("shareList/${item.id}")
                        }
                    )
                }
            }
        }
    }
    LaunchedEffect (key1 = Unit){
        viewModel.loadUsers()
    }
}