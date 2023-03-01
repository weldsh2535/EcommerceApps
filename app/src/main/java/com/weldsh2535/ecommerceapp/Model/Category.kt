package com.weldsh2535.ecommerceapp.Model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlin.random.Random
import kotlin.random.nextInt

@IgnoreExtraProperties
class Category(
    var id: Int = getCategoryId(),
    var parentId: String? = null,
    var title: String? = null,
    var metaTitle: String? = null,
    var slug: String? = null,
    var content: String? = null
){
    @Exclude
    fun toMap():Map<String,Any?>{
        return mapOf(
            "id" to getCategoryId(),
            "parentId" to parentId,
            "title" to title,
            "metaTitle" to metaTitle,
            "slug" to slug,
            "content" to content
        )
    }
}
fun getCategoryId():Int {
    return Random.nextInt(1..300)
}