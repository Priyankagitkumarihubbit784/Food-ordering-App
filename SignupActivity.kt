package com.example.foodorderingapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore

    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var phone: EditText
    lateinit var address: EditText
    lateinit var password: EditText

    lateinit var signupBtn: Button
    lateinit var loginText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        phone = findViewById(R.id.phone)
        address = findViewById(R.id.address)
        password = findViewById(R.id.password)

        signupBtn = findViewById(R.id.signupBtn)
        loginText = findViewById(R.id.loginText)

        signupBtn.setOnClickListener {

            val userName = name.text.toString()
            val userEmail = email.text.toString()
            val userPhone = phone.text.toString()
            val userAddress = address.text.toString()
            val userPass = password.text.toString()

            if (userName.isEmpty() || userEmail.isEmpty() || userPhone.isEmpty()
                || userAddress.isEmpty() || userPass.isEmpty()
            ) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(userEmail, userPass)
                .addOnCompleteListener {

                    if (it.isSuccessful) {

                        val userId = auth.currentUser!!.uid

                        val userMap = hashMapOf(
                            "name" to userName,
                            "email" to userEmail,
                            "phone" to userPhone,
                            "address" to userAddress
                        )

                        db.collection("users")
                            .document(userId)
                            .set(userMap)

                        Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()

                    } else {
                        Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        loginText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}