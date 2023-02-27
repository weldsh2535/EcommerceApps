package com.weldsh2535.ecommerceapp.Model

import java.util.Date

data class Transaction(
    var id: String,
    var userId: String,
    var orderId: String,
    var code: String,
    var type: String,
    var mode: String,
    var status: Int,
    var createdAt: Date,
    var updatedAt: Date,
    var content: String
)