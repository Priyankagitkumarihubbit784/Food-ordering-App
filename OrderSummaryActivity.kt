package com.example.foodorderingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class OrderSummaryActivity : AppCompatActivity() {

    lateinit var totalBill: TextView
    lateinit var addressText: TextView
    lateinit var placeOrderBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)

        totalBill = findViewById(R.id.totalBill)
        addressText = findViewById(R.id.addressText)
        placeOrderBtn = findViewById(R.id.placeOrderBtn)

        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        // CALCULATE TOTAL BILL
        var total = 0

        for (item in CartManager.cartItems) {

            val price = item.price.replace("₹", "").toInt()
            total += price * item.quantity

        }

        totalBill.text = "Total Bill: ₹$total"

        // FETCH ADDRESS FROM FIREBASE
        val userId = auth.currentUser?.uid

        if (userId != null) {

            db.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener { document ->

                    val address = document.getString("address")

                    if (address != null) {
                        addressText.text = address
                    } else {
                        addressText.text = "No address found"
                    }

                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to load address", Toast.LENGTH_SHORT).show()
                }
        }

        // PLACE ORDER
        placeOrderBtn.setOnClickListener {

            if (CartManager.cartItems.isEmpty()) {
                Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            startActivity(Intent(this, OrderSuccessActivity::class.java))

            CartManager.cartItems.clear()
        }
    }
}