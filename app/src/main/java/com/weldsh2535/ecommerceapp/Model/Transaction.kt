package com.weldsh2535.ecommerceapp.Model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt

@IgnoreExtraProperties
data class Transaction(
    var id: Int = getTransactionId(),
    var userId: String,
    var orderId: String,
    var code: String,
    var type: String,
    var mode: String,
    var status: Int,
    var createdAt: Date,
    var updatedAt: Date,
    var content: String
){
    @Exclude
    fun toMap():Map<String,Any?> {
        return mapOf(
            "id" to getOrderId(),
            "userId" to userId,
            "orderId" to orderId,
            "code" to code,
            "type" to type,
            "mode" to mode,
            "status" to status,
            "createdAt" to createdAt,
            "updatedAt" to updatedAt,
            "content" to content
        )
    }
}

fun getTransactionId():Int {
    return Random.nextInt(1..800)
}