package com.example.foodorderingapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var foodList: ArrayList<FoodModel>

    lateinit var cartBtn: ImageView
    lateinit var profileBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerFood)

        // Top buttons
        cartBtn = findViewById(R.id.cartBtn)
        profileBtn = findViewById(R.id.profileBtn)

        foodList = ArrayList()

        // FOOD ITEMS
        foodList.add(FoodModel("Cheese Burger", "₹149", R.drawable.burger))
        foodList.add(FoodModel("Veg Pizza", "₹199", R.drawable.pizza))
        foodList.add(FoodModel("Pasta", "₹179", R.drawable.pasta))
        foodList.add(FoodModel("Sandwich", "₹129", R.drawable.sandwich))
        foodList.add(FoodModel("Fries", "₹99", R.drawable.fries))
        foodList.add(FoodModel("Noodles", "₹159", R.drawable.noodles))

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = FoodAdapter(foodList)
        recyclerView.adapter = adapter

        // FOOD CLICK → OPEN DETAIL PAGE
        adapter.setOnItemClickListener(object : FoodAdapter.OnItemClickListener {
            override fun onClick(position: Int) {

                val intent = Intent(this@HomeActivity, FoodDetailActivity::class.java)

                intent.putExtra("name", foodList[position].name)
                intent.putExtra("price", foodList[position].price)
                intent.putExtra("image", foodList[position].image)

                startActivity(intent)
            }
        })

        // CART PAGE
        cartBtn.setOnClickListener {

            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)

        }

        // PROFILE PAGE
        profileBtn.setOnClickListener {

            val intent = Intent(this@HomeActivity, ProfileActivity::class.java)
            startActivity(intent)

        }
    }
}