package com.weldsh2535.ecommerceapp.Model

import com.google.firebase.firestore.Exclude
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class Cart_Item(
    var id: Int = getCartItemId(),
    var productId: String,
    var cartId: String,
    var sku: String,
    var price: Float,
    var discount: Float,
    var quantity: Float,
    var active: String,
    var createdAt: Date,
    var updatedAt: Date,
    var content: String
){
    @Exclude
    fun toMap():Map<String,Any> {
        return mapOf(
            "id" to id,
            "productId" to productId,
            "cartId" to cartId,
            "sku" to sku,
            "price" to price,
            "discount" to discount,
            "quantity" to quantity,
            "active" to active,
            "createdAt" to createdAt,
            "updatedAt" to updatedAt,
            "content" to content
        )
    }

}
fun getCartItemId():Int {
    return Random.nextInt(1..2000)
}