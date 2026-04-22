package com.example.foodorderingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OrderSuccessActivity : AppCompatActivity() {

    lateinit var homeBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_success)

        homeBtn = findViewById(R.id.homeBtn)

        homeBtn.setOnClickListener {

            startActivity(Intent(this, HomeActivity::class.java))
            finish()

        }
    }
}