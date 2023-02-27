package com.weldsh2535.ecommerceapp.Model

import java.util.*

class Product_review(
    var id: String,
    var productId: String,
    var parentId: String,
    var title: String,
    var rating: Int,
    var published: String,
    var createdAt: Date,
    var publishedAt: Date,
    var content: String
)