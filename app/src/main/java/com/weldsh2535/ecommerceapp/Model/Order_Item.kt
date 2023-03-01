package com.weldsh2535.ecommerceapp.Model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt

@IgnoreExtraProperties
data class Order_Item(
    var id: Int = getOrderItemId(),
    var productId: String,
    var orderId: String,
    var sku: String,
    var price: Float,
    var discount: Float,
    var quantity: Float,
    var createdAt: Date,
    var updatedAt: Date,
    var content: String
){
    @Exclude
    fun toMap():Map<String,Any?> {
        return mapOf(
            "id" to getOrderItemId(),
            "productId" to productId,
            "orderId" to orderId,
            "sku" to sku,
            "price" to price,
            "discount" to discount,
            "quantity" to quantity,
            "createdAt" to createdAt,
            "updatedAt" to updatedAt,
            "content" to content
        )
    }
}
fun getOrderItemId():Int {
    return Random.nextInt(1..2000)
}