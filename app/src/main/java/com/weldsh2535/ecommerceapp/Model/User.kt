package com.weldsh2535.ecommerceapp.Model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt

@IgnoreExtraProperties
data class User(
    var id: String = getId(),
    var firstName: String? = null,
    var lastName: String? = null,
    var mobile: String? = null,
    var userName: String? = null,
    var email: String? = null,
    var registeredAt: String? = null,
    var lastLogin: String? = null
){
    @Exclude
    fun toMap():Map<String,Any?>{
        return mapOf(
            "id" to getId(),
            "firstName" to firstName,
            "lastName" to lastName,
            "moblie" to mobile,
            "userName" to userName,
            "email" to email,
            "registeredAt" to registeredAt,
            "lastLogin" to lastLogin
        )
    }
}

fun getId(): String {
    return Random.nextInt(1..300).toString()
}

