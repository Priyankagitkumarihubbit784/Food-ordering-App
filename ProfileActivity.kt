package com.example.foodorderingapp

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore

    lateinit var userName: TextView
    lateinit var userEmail: TextView
    lateinit var userPhone: TextView
    lateinit var userAddress: TextView

    lateinit var logoutBtn: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        userName = findViewById(R.id.userName)
        userEmail = findViewById(R.id.userEmail)
        userPhone = findViewById(R.id.userPhone)
        userAddress = findViewById(R.id.userAddress)

        logoutBtn = findViewById(R.id.logoutBtn)

        val userId = auth.currentUser?.uid

        if (userId != null) {

            db.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener {

                    userName.text = it.getString("name")
                    userEmail.text = it.getString("email")
                    userPhone.text = it.getString("phone")
                    userAddress.text = it.getString("address")

                }
        }

        logoutBtn.setOnClickListener {

            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}