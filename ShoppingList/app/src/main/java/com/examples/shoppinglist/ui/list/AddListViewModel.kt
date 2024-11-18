package com.examples.shoppinglist.ui.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.examples.shoppinglist.data.models.ListItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

data class AddListState(
    var name: String = "",
    var description: String = "",
    val isLoading: Boolean = false,
    var errorMessage: String? = null
)

class AddListViewModel : ViewModel() {
    var state = mutableStateOf(AddListState())
        private set

    private val db = FirebaseFirestore.getInstance()

    private val auth = FirebaseAuth.getInstance()

    fun onNameChange(newValue: String) {
        state.value = state.value.copy(name = newValue)
    }

    fun onDescriptionChange(newValue: String) {
        state.value = state.value.copy(description = newValue)
    }
}