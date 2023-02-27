package com.weldsh2535.ecommerceapp.Model

import java.util.Date

class Product(
    var id: String,
    var userId: String,
    var title: String,
    var metaTitle: String,
    var slug: String,
    var summary: String,
    var type: String,
    var sku: String,
    var price: Float,
    var discount: Float,
    var quantity: Int,
    var shop: String,
    var createdAt: Date,
    var updatedAt: Date,
    var publishedAt: Date,
    var startsAt: Date,
    var endsAt: Date,
    var content: String
)