package com.weldsh2535.ecommerceapp.Model

import java.util.*

class Cart_Item(
    var id: String,
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
)