package com.weldsh2535.ecommerceapp.Model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlin.random.Random
import kotlin.random.nextInt

@IgnoreExtraProperties
class Product_meta(
    var id: Int = getProductMetaId(),
    var productId: String,
    var key: String,
    var content: String
){
    @Exclude
    fun toMap():Map<String,Any?> {
        return mapOf(
            "id" to getProductMetaId(),
            "productId" to productId,
            "key" to key,
            "content" to content
        )
    }
}
fun getProductMetaId():Int {
    return Random.nextInt(1..2000)
}