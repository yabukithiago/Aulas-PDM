package com.examples.shoppinglist.ui.item

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class AddNewItemState(
    val name: String = "",
    val quantity: String = "",
    val isLoading: Boolean = false,
    var errorMessage: String? = null
)

class AddNewItemViewModel : ViewModel() {
    var state = mutableStateOf(AddNewItemState())
        private set

    fun onNameChange(name: String){
        state.value = state.value.copy(
            name = name
        )
    }

    fun onQuantityChange(quantity: String){
        state.value = state.value.copy(
            quantity = quantity
        )
    }
}