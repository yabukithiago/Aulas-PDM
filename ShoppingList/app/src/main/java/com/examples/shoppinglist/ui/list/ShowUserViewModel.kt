package com.examples.shoppinglist.ui.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.examples.shoppinglist.data.models.User
import com.examples.shoppinglist.data.repository.UserRepository.getAllUsers

data class ShowUserState(
    val listUsers: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ShowUserViewModel : ViewModel() {
    var state = mutableStateOf(ShowUserState())
        private set

    fun loadUsers() {
        getAllUsers { listUsers ->
            state.value = state.value.copy(
                listUsers = listUsers
            )
        }
    }
}