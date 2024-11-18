package com.examples.shoppinglist.data.repository

import android.annotation.SuppressLint
import com.examples.shoppinglist.data.models.ListItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("StaticFieldLeak")
object ListItemRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun getAll(onSuccess: (List<ListItem>) -> Unit){
        db.collection("listTypes")

            .addSnapshotListener { value, _ ->
                val listItems = mutableListOf<ListItem>()
                value?.let{
                    for (document in it.documents) {
                        document.data?.let { it1 ->
                            listItems.add(ListItem.fromMap(it1))
                        }
                    }
                }
                onSuccess(listItems)
            }
    }

    fun addList(name: String, description: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit
    ) {
        val uid = auth.currentUser?.uid

        if (uid == null) {
            onFailure("Usuário não autenticado")
            return
        }

        val lists = ListItem(id = "", name = name, description = description)

        db.collection("listTypes")
            .add(lists)
            .addOnSuccessListener { documentReference ->
                val generatedId = documentReference.id
                db.collection("listTypes")
                    .document(generatedId)
                    .update("id", generatedId)
                    .addOnSuccessListener {
                        onSuccess("Lista criada com sucesso com ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        onFailure("Erro ao adicionar lista: ${e.message}")
                    }
            }
            .addOnFailureListener { e ->
                onFailure("Erro ao registrar no Firestore: ${e.message}")
            }
    }

    fun updateList(
        id: String,
        nome: String,
        description: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val updates = mapOf(
            "nome" to nome,
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
}