package com.examples.shoppinglist.data.models

data class Item(
    var name: String = "",
    var quantity: String = ""

) {
    companion object {
        fun fromMap(map: Map<String, Any?>) : Item {
            return Item(
                map["name"] as String,
                map["quantity"] as String
            )
        }
    }
}