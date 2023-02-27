package com.weldsh2535.ecommerceapp.Model

import java.util.Date

data class Order_Item(
    var id: String,
    var productId: String,
    var orderId: String,
    var sku: String,
    var price: Float,
    var discount: Float,
    var quantity: Float,
    var createdAt: Date,
    var updatedAt: Date,
    var content: String
)