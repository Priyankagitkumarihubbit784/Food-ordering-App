package com.example.foodorderingapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottomNav)

        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                }

                R.id.cart -> {
                    startActivity(Intent(this, CartActivity::class.java))
                }

                R.id.profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                }

            }

            true
        }
    }
}