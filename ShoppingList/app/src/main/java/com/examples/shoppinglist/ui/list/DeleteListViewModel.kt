package com.examples.shoppinglist.ui.list

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.examples.shoppinglist.data.repository.ListItemRepository

class DeleteListViewModel : ViewModel() {
    fun deleteList(id: String, onFailure: (Exception) -> Unit) {
        ListItemRepository.deleteList(
            id = id,
            onSuccess = { NavController },
            onFailure = { exception ->
                onFailure(exception)
            })
    }
}