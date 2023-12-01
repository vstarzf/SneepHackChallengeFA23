package com.example.dreamsleepapp

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SleepAdapter (private val dataSet:List<Sleep>) : RecyclerView.Adapter<SleepAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val date : TextView = view.findViewById(R.id.dateText)
        val hrsSlept : TextView = view.findViewById(R.id.hoursSlept)
        val image : ImageView = view.findViewById(R.id.ratingImg)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sleep_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: SleepAdapter.ViewHolder, position: Int) {
        val sleep = dataSet[position]
        holder.date.text = dataSet[position].date
        holder.hrsSlept.text = dataSet[position].hrs

        if(!holder.hrsSlept.text.toString().equals("") && holder.hrsSlept.text.toString().toInt() <= 4 ) {
            holder.image.setImageResource(R.drawable.sadmoon)
        } else {
            holder.image.setImageResource(R.drawable.happymoon)
        }
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            if (context is MainActivity) { // or YourFragment if it's used in a fragment
                context.openDreamLogFragment(sleep)
            }

        }


    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}