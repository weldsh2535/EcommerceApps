package com.weldsh2535.ecommerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weldsh2535.ecommerceapp.SignIn.Signin
import com.weldsh2535.ecommerceapp.databinding.ActivityMainScreenBinding

class MainScreen : AppCompatActivity() {
    private lateinit var binding:ActivityMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this@MainScreen,Signin::class.java)
            startActivity(intent)
        }
    }
}