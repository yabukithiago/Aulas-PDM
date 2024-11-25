package com.examples.shoppinglist.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examples.shoppinglist.data.models.Item
import com.examples.shoppinglist.data.repository.ItemRepository.fetchItems
import com.examples.shoppinglist.ui.components.TopBar

@Composable
fun ShowItemView(navController: NavController, listId: String) {
    var itemList by remember { mutableStateOf(emptyList<Item>()) }


    LaunchedEffect(listId) {
        fetchItems(
            listTypeId = listId,
            onSuccess = { fetchedItems ->
                itemList = fetchedItems
            },
            onFailure = {

            }
        )
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        TopBar("Listas", navController = navController)
        LazyColumn(contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(itemList) { item ->
                Text(
                    text = "${item.name} - ${item.quantity}",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}