package com.examples.shoppinglist.data.repository

import com.examples.shoppinglist.data.models.Item
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

object ItemRepository {
    private val db by lazy { Firebase.firestore }

    fun getAll(onSuccess: (List<Item>) -> Unit){
        db.collection("listTypes/{id}/items")

            .addSnapshotListener { value, _ ->
                val items = mutableListOf<Item>()
                value?.let{
                    for (document in it.documents) {
                        document.data?.let { it1 ->
                            items.add(Item.fromMap(it1))
                        }
                    }
                }
                onSuccess(items)
            }
    }

    fun fetchItems(
        listTypeId: String,
        onSuccess: (List<Item>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("listTypes")
            .document(listTypeId)
            .collection("items")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val items = querySnapshot.documents.mapNotNull { it.toObject(Item::class.java) }
                onSuccess(items)
            }
            .addOnFailureListener { e ->
                onFailure("Erro ao buscar itens: ${e.message}")
            }
    }

    fun addItem(
        listTypeId: String, name: String, quantity: String,
        onSuccess: (String) -> Unit, onFailure: (String) -> Unit){
        val listItem = Item(name = name, quantity = quantity)

        ListItemRepository.db.collection("listTypes")
            .document(listTypeId)
            .collection("items")
            .add(listItem)
            .addOnSuccessListener {
                onSuccess("Lista criada com sucesso com ID")
            }
            .addOnFailureListener {
                onFailure("Erro ao criar no firebase")
            }
    }

    fun countItems(listTypeId: String, onSuccess: (Int) -> Unit, onFailure: (String) -> Unit) {
        db.collection("listTypes")
            .document(listTypeId)
            .collection("items")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val itemCount = querySnapshot.documents.size
                onSuccess(itemCount)
            }
            .addOnFailureListener { e ->
                onFailure("Erro ao contar itens: ${e.message}")
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
}