package com.weldsh2535.ecommerceapp.Model

import kotlin.random.Random
import kotlin.random.nextInt

class Category(
    var id: Int = getCategoryId(),
    var parentId: String,
    var title: String,
    var metaTitle: String,
    var slug: String,
    var content: String
)
fun getCategoryId():Int {
    return Random.nextInt(1..300)
}