package com.weldsh2535.ecommerceapp.Model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt

@IgnoreExtraProperties
class Product(
    var id: String = getGenerateId(),
    var userId: String? = null,
    var title: String? = null,
    var metaTitle: String? = null,
    var slug: String? = null,
    var summary: String? = null,
    var type: String? = null,
    var price: Float? = null,
    var discount: Float? = null,
    var quantity: Int? = null,
    var shop: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var publishedAt: String? = null,
    var startsAt: String? = null,
    var endsAt: String? = null,
    var content: String? = null
){
    @Exclude
    fun toMap():Map<String,Any?>{
        return mapOf(
            "id" to getGenerateId(),
            "userId" to userId,
            "title" to title,
            "metaTitle" to metaTitle,
            "slug" to slug,
            "summary" to summary,
            "type" to type,
            "price" to price,
            "discount" to discount,
            "quantity" to quantity,
            "shop" to shop,
            "createdAt" to createdAt,
            "updatedAt" to updatedAt,
            "publishedAt" to publishedAt,
            "startsAt" to startsAt,
            "endsAt" to endsAt,
            "content" to content
        )
    }
}
fun getGenerateId(): String {
    return Random.nextInt(1..3000).toString()
}