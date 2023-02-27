package com.weldsh2535.ecommerceapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weldsh2535.ecommerceapp.R
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        //click add to cart button events of functions
        addToCart.setOnClickListener {
            val intent = Intent(this@CartActivity , MyCartActivity::class.java)
            startActivity(intent)
        }

    }
}