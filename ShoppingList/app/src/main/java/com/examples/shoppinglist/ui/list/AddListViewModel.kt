package com.examples.shoppinglist.ui.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.examples.shoppinglist.data.models.ListItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

data class AddListState(
    var name: String = "",
    var description: String = "",
    val numeroVisitas: Int = 0,
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

    fun addList(
        name: String, description: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit
    ) {

        val uid = auth.currentUser?.uid

        if (uid == null) {
            onFailure("Usuário não autenticado")
            return
        }

        val lists = ListItem(name, description)

        db.collection("listTypes")
            .add(lists)
            .addOnSuccessListener { documentReference ->
                onSuccess("Lista criada com sucesso com ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                onFailure("Erro ao registrar no Firestore: ${e.message}")
            }
    }
}