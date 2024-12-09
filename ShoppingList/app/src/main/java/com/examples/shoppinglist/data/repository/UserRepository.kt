package com.examples.shoppinglist.data.repository

import com.examples.shoppinglist.data.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

object UserRepository {
    private val db by lazy { Firebase.firestore }

    fun getAllUsers(onSuccess: (List<User>) -> Unit) {
        db.collection("user")
            .get()
            .addOnSuccessListener { result ->
                val listUsers = mutableListOf<User>()
                for (document in result) {
                    document.data.let { it1 ->
                        listUsers.add(User.fromMap(it1))
                    }
                }
                onSuccess(listUsers)
            }
            .addOnFailureListener {
            }
    }
}