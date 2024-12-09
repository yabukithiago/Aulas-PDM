package com.examples.shoppinglist.ui.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.examples.shoppinglist.data.repository.ListItemRepository

data class DeleteListState(
    var name: String = "",
    var description: String = "",
    val isLoading: Boolean = false,
    var errorMessage: String? = null
)

class DeleteListViewModel : ViewModel() {
    var state = mutableStateOf(AddListState())
        private set

    private val name
        get() = state.value.name
    private val description
        get() = state.value.description

    fun deleteList(id: String, onFailure: (Exception) -> Unit) {
        ListItemRepository.deleteList(
            id = id,
            onSuccess = { NavController },
            onFailure = { exception ->
                onFailure(exception)
            })
    }
}