package com.example.dreamsleepapp

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SleepAdapter (private val dataSet:List<Sleep>) : RecyclerView.Adapter<SleepAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val date : TextView = view.findViewById(R.id.dateText)
        val hrsSlept : TextView = view.findViewById(R.id.hoursSlept)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sleep_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SleepAdapter.ViewHolder, position: Int) {
        val sleep = dataSet[position]
        holder.date.text = dataSet[position].date
        holder.hrsSlept.text = dataSet[position].hrs.toString()
        holder.itemView.setOnClickListener {

            val context = holder.itemView.context
            val intent = Intent(context, EditDreamActivity::class.java)

            intent.putExtra("sleep date", sleep.date)
            intent.putExtra("sleep hours", sleep.hrs)

            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}