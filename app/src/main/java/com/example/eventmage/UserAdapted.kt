package com.example.eventmage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(val users: ArrayList<Users>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(users[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return users.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: Users) {
            val textViewName = itemView.findViewById(R.id.secondLine) as TextView
            val textViewAddress  = itemView.findViewById(R.id.firstLine) as TextView
            textViewName.text = user.name
           textViewAddress.text = user.description
        }
    }
}