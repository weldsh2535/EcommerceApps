package com.weldsh2535.ecommerceapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.weldsh2535.ecommerceapp.R
import kotlinx.android.synthetic.main.activity_my_cart.*

class MyCartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cart)

        btnBack.setOnClickListener{
            val intent = Intent(this@MyCartActivity , CartActivity::class.java)
            startActivity(intent)
        }
        btnDelete.setOnClickListener {
            Toast.makeText(this,"Delete My Cart ",Toast.LENGTH_SHORT).show()
        }
    }
}