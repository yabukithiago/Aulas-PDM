package com.examples.shoppinglist.data.models

data class User(
    var id: String,
    var nome: String,
    var email: String,
    var telefone: String,
) {
    companion object {
        fun fromMap(map: Map<String, Any?>) : User {
            return User(
                id = map["id"] as? String ?: "",
                nome = map["nome"] as? String ?: "Unknown",
                email = map["email"] as? String ?: "No email",
                telefone = map["telefone"] as? String ?: "No telefone",
            )
        }
    }
}