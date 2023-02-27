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
    var middleName: String? = null,
    var lastName: String? = null,
    var mobile: String? = null,
    var email: String? = null,
    var passwordHash: String? = null ,
    var admin: String? = null,
    var vendor: String? = null,
    var registeredAt: Date,
    var lastLogin: Date,
    var intro: String? = null,
    var profile: String? = null
){
    @Exclude
    fun toMap():Map<String,Any?>{
        return mapOf(
            "id" to getId(),
            "firstName" to firstName,
            "middleName" to middleName,
            "lastName" to lastName,
            "moblie" to mobile,
            "email" to email,
            "passwordHash" to passwordHash,
            "admin" to admin,
            "vendor" to vendor,
            "registeredAt" to registeredAt,
            "lastLogin" to lastLogin,
            "intro" to intro,
            "profile" to profile
        )
    }
}

fun getId(): String {
    return Random.nextInt(1..300).toString()
}

