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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.examples.shoppinglist.ui.cards.ListCard
import com.examples.shoppinglist.ui.components.TopBar

@Composable
fun ShowListsView(navController: NavController, modifier: Modifier = Modifier) {
    val viewModel: ShowListItemsViewModel = viewModel()
    val state by viewModel.state

    Box(modifier = modifier.fillMaxSize()) {
        Column {
            TopBar(title = "Your lists", navController = navController)
            LazyColumn(contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(
                    items = state.listItems
                ) { _, item ->
                    ListCard (
                        navController = navController,
                        id = item.id,
                        name = item.name,
                        description = item.description,
                        onClick = {
                            navController.navigate("showItems/${item.id}")
                        }
                    )
                }
            }
        }
    }
    LaunchedEffect (key1 = Unit){
        viewModel.loadListItems()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShowVoluntaryView(){
    ShowListsView(navController = rememberNavController())
}
