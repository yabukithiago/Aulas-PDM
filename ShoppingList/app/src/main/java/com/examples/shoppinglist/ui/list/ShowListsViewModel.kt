package com.examples.shoppinglist.ui.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.examples.shoppinglist.data.models.Item
import com.examples.shoppinglist.data.models.ListItem
import com.examples.shoppinglist.data.repository.ListItemRepository

data class ShowListItemsState(
    val listItems: List<ListItem> = emptyList(),
    val items: List<Item> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ShowListItemsViewModel : ViewModel(){
    var state = mutableStateOf(ShowListItemsState())
        private set

    fun loadListItems(){
        ListItemRepository.getAll{ listItems ->
            state.value = state.value.copy(
                listItems = listItems
            )
        }
    }
}