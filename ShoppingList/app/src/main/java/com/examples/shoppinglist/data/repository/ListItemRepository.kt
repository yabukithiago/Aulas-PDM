package com.examples.shoppinglist.data.repository

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import com.examples.shoppinglist.data.models.ListItem
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("StaticFieldLeak")
object ListItemRepository {

    private val db = FirebaseFirestore.getInstance()

    fun getAll(onSuccess: (List<ListItem>) -> Unit){
        db.collection("listTypes")

            .addSnapshotListener { value, error ->
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

}