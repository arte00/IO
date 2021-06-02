package com.example.aplikacjaio

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.ub_item.view.*
import org.w3c.dom.Text

// import androidx.recyclerview.widget.RecyclerView.Adapter

class AdapterUserBuses(private val itemList: List<UserBusItem>) : RecyclerView.Adapter<AdapterUserBuses.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ub_item,
            parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]

        // Transfer data to holder

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.lineString
        holder.textView2.text = currentItem.model
        holder.textView3.text = currentItem.id.toString()

        // Set color access
        /*
        There are three parameters that define access
        8-bit code [0 1]
        * access for people with disability [1st bit]
        * access for elderly people [2nd bit]
        * access for people with strollers / little child [3rd bit]

        example value:
        110 (6[10])-> access for people with disabilities, asses for elderly people, no access for strollers
        therefore
        */

        // set white

        when(currentItem.access){
            0 -> {
                holder.accessIcon1.setColorFilter(Color.rgb(255, 255, 255))
                holder.accessIcon2.setColorFilter(Color.rgb(255, 255, 255))
                holder.accessIcon3.setColorFilter(Color.rgb(255, 255, 255))
            }
            1 -> {
                holder.accessIcon1.setColorFilter(Color.rgb(255, 255, 255))
                holder.accessIcon2.setColorFilter(Color.rgb(255, 255, 255))
            }
            2 -> {
                holder.accessIcon1.setColorFilter(Color.rgb(255, 255, 255))
                holder.accessIcon3.setColorFilter(Color.rgb(255, 255, 255))
            }
            3 -> {
                holder.accessIcon1.setColorFilter(Color.rgb(255, 255, 255))
            }
            4 -> {
                holder.accessIcon2.setColorFilter(Color.rgb(255, 255, 255))
                holder.accessIcon3.setColorFilter(Color.rgb(255, 255, 255))
            }
            5 -> {
                holder.accessIcon2.setColorFilter(Color.rgb(255, 255, 255))
            }
            6 -> {
                holder.accessIcon3.setColorFilter(Color.rgb(255, 255, 255))
            }
        }



    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.ub_item_icon
        val textView1: TextView = itemView.ub_item_line
        val textView2: TextView = itemView.ub_item_model
        val textView3: TextView = itemView.ub_item_id
        val accessIcon1: ImageView = itemView.ub_item_disabled
        val accessIcon2: ImageView = itemView.ub_item_elderly
        val accessIcon3: ImageView = itemView.ub_item_strollers
    }

    override fun getItemCount() = itemList.size

}