package com.weldsh2535.ecommerceapp.Model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

@IgnoreExtraProperties
class Product_review(
    var id: Int = getProductReviewId(),
    var productId: String,
    var parentId: String,
    var title: String,
    var rating: Int,
    var createdAt: Date,
    var publishedAt: Date,
    var content: String
){
    @Exclude
    fun toMap():Map<String,Any?> {
        return mapOf(
            "id" to getProductReviewId(),
            "productId" to productId,
            "parentId" to parentId,
            "title" to title,
            "rating" to rating,
            "publishedAt"  to publishedAt,
            "createdAt" to createdAt,
            "content" to content
        )
    }
}

fun getProductReviewId():Int {
    return Random.nextInt(1..300)
}