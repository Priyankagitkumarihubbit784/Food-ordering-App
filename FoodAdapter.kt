package com.example.foodorderingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private val foodList: ArrayList<FoodModel>) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    class ViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.foodName)
        val price: TextView = itemView.findViewById(R.id.foodPrice)
        val image: ImageView = itemView.findViewById(R.id.foodImage)

        init {
            itemView.setOnClickListener {
                listener.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)

        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val food = foodList[position]

        holder.name.text = food.name
        holder.price.text = food.price
        holder.image.setImageResource(food.image)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}