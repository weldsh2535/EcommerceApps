package com.weldsh2535.ecommerceapp.Model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
class Product_category(
    var productId: String,
    var categotyId: String
){
    @Exclude
    fun toMap():Map<String,Any?> {
        return mapOf(
            "productId" to productId,
            "categotyId" to categotyId
        )
    }
}