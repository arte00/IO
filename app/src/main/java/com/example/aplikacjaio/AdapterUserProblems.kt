package com.example.aplikacjaio

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
// import androidx.recyclerview.widget.RecyclerView.Adapter

class AdapterUserProblems(private val itemList: List<UserProblemItem>) : RecyclerView.Adapter<AdapterUserProblems.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.up_item,
        parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]

        // Transfer data to holder

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2

        // Set color

        when(currentItem.priority){
            1 -> holder.imageView.setColorFilter(Color.rgb(237, 41, 56))
            2 -> holder.imageView.setColorFilter(Color.rgb(255, 140, 1))
            3, 4 -> holder.imageView.setColorFilter(Color.rgb(2, 78, 27))
        }


    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.up_item_icon)
        val textView1: TextView = itemView.findViewById(R.id.up_item_title)
        val textView2: TextView = itemView.findViewById(R.id.up_item_shExplanation)
    }

    override fun getItemCount() = itemList.size

}