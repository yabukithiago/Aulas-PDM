package com.examples.shoppinglist.ui.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.examples.shoppinglist.data.models.ListItem
import com.examples.shoppinglist.data.repository.ListItemRepository

data class AddListState(
    var name: String = "",
    var description: String = "",
    val isLoading: Boolean = false,
    var errorMessage: String? = null
)

class AddListViewModel : ViewModel() {
    var state = mutableStateOf(AddListState())
        private set

    private val name
        get() = state.value.name
    private val description
        get() = state.value.description

    fun onNameChange(newValue: String) {
        state.value = state.value.copy(name = newValue)
    }

    fun onDescriptionChange(newValue: String) {
        state.value = state.value.copy(description = newValue)
    }

    fun addList(){
        ListItemRepository.addList(listItem = ListItem("", name, description, null),
            onSuccess = { NavController })
    }
}