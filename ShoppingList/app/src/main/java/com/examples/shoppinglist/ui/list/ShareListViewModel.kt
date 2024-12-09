package com.examples.shoppinglist.ui.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.examples.shoppinglist.data.models.User
import com.examples.shoppinglist.data.repository.ListItemRepository
import com.examples.shoppinglist.data.repository.UserRepository.getAllUsers

data class ShareListState(
    val listUsers: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ShareListViewModel : ViewModel(){
    var state = mutableStateOf(ShareListState())
        private set

    fun loadUsers(){
        getAllUsers{ listUsers ->
            state.value = state.value.copy(
                listUsers = listUsers
            )
        }
    }
    fun shareListWithUser(listId: String, userId: String) {
        ListItemRepository.shareList(listId, userId,
            onSuccess = { /* Sucesso */ },
            onFailure = { /* Falha */ }
        )
    }

}