package com.example.foodorderingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(val list:ArrayList<CartModel>) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val name = itemView.findViewById<TextView>(R.id.cartName)
        val price = itemView.findViewById<TextView>(R.id.cartPrice)
        val image = itemView.findViewById<ImageView>(R.id.cartImage)

    }

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position:Int) {

        val item = list[position]

        holder.name.text = item.name
        holder.price.text = item.price
        holder.image.setImageResource(item.image)

    }

    override fun getItemCount():Int{
        return list.size
    }
}