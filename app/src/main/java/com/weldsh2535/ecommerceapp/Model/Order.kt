package com.weldsh2535.ecommerceapp.Model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

@IgnoreExtraProperties
class Order(
    var id: Int = getOrderId(),
    var userId: String? = null,
    var sessionId: String? = null,
    var token: String? = null,
    var status: String? = null,
    var subTotal: Float? = null,
    var itemDiscount: Float? = null,
    var tax: Float? = null,
    var shipping: Float? = null,
    var total: Float? = null,
    var promo: String? = null,
    var discount: Int? = null,
    var grandTotal: Int? = null,
    var firstName: String? = null,
    var middleName: String? = null,
    var lastName: String? = null,
    var mobile: String? = null,
    var email: String? = null,
    var line1: String? = null,
    var line2: String? = null,
    var city: String? = null,
    var province: String? = null,
    var country: String? = null,
    var createdAt: Date? = null,
    var updatedAt: Date? = null,
    var content: String? = null
){
    @Exclude
    fun toMap():Map<String,Any?> {
        return mapOf(
            "id" to id,
            "userId" to userId,
            "sessionId" to sessionId,
            "token" to token,
            "status" to status,
            "subTotal" to subTotal,
            "itemDiscount" to itemDiscount,
            "tax" to tax,
            "shipping" to shipping,
            "total" to total,
            "promo" to promo,
            "discount" to discount,
            "grandTotal" to grandTotal,
            "firstName" to firstName,
            "middleName" to middleName,
            "lastName" to lastName,
            "mobile" to mobile,
            "email" to email,
            "line1" to line1,
            "line2" to line2,
            "city" to city,
            "province" to province,
            "country" to country,
            "createdAt" to createdAt,
            "updatedAt" to updatedAt,
            "content" to content
        )
    }
}
fun getOrderId():Int {
    return Random.nextInt(1..1000)
}
