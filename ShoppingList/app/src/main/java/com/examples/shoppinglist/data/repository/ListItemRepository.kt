package com.examples.shoppinglist.data.repository

import android.annotation.SuppressLint
import com.examples.shoppinglist.data.models.ListItem
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

object ListItemRepository {
    private val auth by lazy { FirebaseAuth.getInstance() }
    val db by lazy { Firebase.firestore }

    fun getAll(onSuccess: (List<ListItem>) -> Unit){
        val currentUser = auth.currentUser
        currentUser?.let { user->
            db.collection("listTypes")
                .whereArrayContains("owner", user.uid)
                .get()
                .addOnSuccessListener { result ->
                    val listItems = mutableListOf<ListItem>()
                    for (document in result) {
                        document.data?.let { it1 ->
                            listItems.add(ListItem.fromMap(it1))
                        }
                    }
                    onSuccess(listItems)
                }
                .addOnFailureListener { exception ->
                }
        }
    }


    fun addList(listItem: ListItem, onSuccess: () -> Unit) {
        val uid = auth.currentUser?.uid
        val currentUser = auth.currentUser

        currentUser?.uid?.let{
            listItem.owner = arrayListOf(it)
        }
        if (uid == null) {
            return
        }

        db.collection("listTypes")
            .add(listItem)
            .addOnSuccessListener { documentReference ->
                val generatedId = documentReference.id
                db.collection("listTypes")
                    .document(generatedId)
                    .update("id", generatedId)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener {
                    }
            }
            .addOnFailureListener {
            }
    }



    fun updateList(id: String, name: String, description: String, onSuccess: (String) -> Unit,
                   onFailure: (String) -> Unit
    ) {
        val updates = mapOf(
            "name" to name,
            "description" to description,
        )

        db.collection("listTypes")
            .document(id)
            .update(updates)
            .addOnSuccessListener {
                onSuccess("Lista editada com sucesso: $id")
            }
            .addOnFailureListener { e ->
                onFailure("Erro ao atualizar lista: ${e.message}")
            }
    }

    fun deleteList(id: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("listTypes").document(id)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    fun shareList(listId: String, userIdToShare: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("listTypes").document(listId)
            .update("owner", FieldValue.arrayUnion(userIdToShare)) // Adiciona o ID ao array
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}