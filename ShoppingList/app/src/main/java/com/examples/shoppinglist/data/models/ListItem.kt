package com.examples.shoppinglist.data.models

data class ListItem(
    var id: String,
    var name: String,
    var description: String,
    var owner: List<*>?

) {
    companion object {
        fun fromMap(map: Map<String, Any?>) : ListItem {
            return ListItem(
                map["id"] as String,
                map["name"] as String,
                map["description"] as String,
                map["owner"] as List<*>
            )
        }
    }
}