package com.example.foodorderingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    lateinit var recyclerCart: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerCart = findViewById(R.id.recyclerCart)

        recyclerCart.layoutManager = LinearLayoutManager(this)

        // USE GLOBAL CART LIST
        recyclerCart.adapter = CartAdapter(CartManager.cartItems)

        val checkoutBtn = findViewById<Button>(R.id.checkoutBtn)

        checkoutBtn.setOnClickListener {

            startActivity(Intent(this, OrderSuccessActivity::class.java))

        }

        checkoutBtn.setOnClickListener {

            if (CartManager.cartItems.isEmpty()){

                Toast.makeText(this,"Cart is empty",Toast.LENGTH_SHORT).show()

            }else{

                startActivity(Intent(this, OrderSummaryActivity::class.java))

            }

        }

    }
}