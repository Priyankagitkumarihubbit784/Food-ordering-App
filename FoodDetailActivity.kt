package com.example.foodorderingapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class FoodDetailActivity : AppCompatActivity() {

    lateinit var foodImage: ImageView
    lateinit var foodName: TextView
    lateinit var foodPrice: TextView
    lateinit var addToCart: Button
    lateinit var sizeGroup: RadioGroup

    var image:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)

        foodImage = findViewById(R.id.foodImage)
        foodName = findViewById(R.id.foodName)
        foodPrice = findViewById(R.id.foodPrice)
        addToCart = findViewById(R.id.addToCart)
        sizeGroup = findViewById(R.id.sizeGroup)

        val name = intent.getStringExtra("name") ?: ""
        val price = intent.getStringExtra("price") ?: "₹0"
        image = intent.getIntExtra("image",0)

        foodName.text = name
        foodPrice.text = price
        foodImage.setImageResource(image)

        var basePrice = price.replace("₹","").toInt()

        // SIZE SELECTION
        sizeGroup.setOnCheckedChangeListener { _, checkedId ->

            when(checkedId){

                R.id.smallSize -> {
                    foodPrice.text = "₹$basePrice"
                }

                R.id.mediumSize -> {
                    foodPrice.text = "₹${basePrice + 40}"
                }

                R.id.largeSize -> {
                    foodPrice.text = "₹${basePrice + 80}"
                }
            }
        }

        // ADD TO CART
        addToCart.setOnClickListener {

            val itemName = foodName.text.toString()
            val itemPrice = foodPrice.text.toString()

            val existingItem = CartManager.cartItems.find { it.name == itemName }

            if(existingItem != null){

                existingItem.quantity++

                Toast.makeText(this,"Quantity Updated",Toast.LENGTH_SHORT).show()

            }else{

                val cartItem = CartModel(
                    itemName,
                    itemPrice,
                    image,
                    1
                )

                CartManager.cartItems.add(cartItem)

                Toast.makeText(this,"Added to Cart",Toast.LENGTH_SHORT).show()
            }
        }
    }
}