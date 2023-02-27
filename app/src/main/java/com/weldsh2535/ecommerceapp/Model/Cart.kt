package com.weldsh2535.ecommerceapp.Model

import java.util.Date

data class Cart(
    var id: String,
    var userId: String,
    var sessionId: String,
    var token: String,
    var status: Int,
    var firstName: String,
    var middleName: String,
    var lastName: String,
    var mobile: String,
    var email: String,
    var line1: String,
    var line2: String,
    var city: String,
    var province: String,
    var country: String,
    var createdAt: Date,
    var updatedAt: Date,
    var content: String
)