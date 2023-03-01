package com.weldsh2535.ecommerceapp.Model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt

@IgnoreExtraProperties
data class Cart(
    var id: Int = getCartId(),
    var userId: String? = null,
    var sessionId: String? = null,
    var token: String? = null,
    var status: Int? = null,
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
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to getCartId(),
            "userId" to userId,
            "sessionId" to sessionId,
            "token" to token,
            "status" to status,
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

fun getCartId(): Int {
    return Random.nextInt(1..300)
}