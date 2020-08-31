package com.aldemir.unittest.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aldemir.unittest.R

class MainAdapter(private val context: Context): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val arraylist = arrayListOf("Aldemir Gomes", "Eleonai Gomes", "Layssa Kesia", "Lorena Sophia")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_main_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = arraylist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arraylist[position]
        holder.name.text = item
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val name: TextView = view.findViewById(R.id.text_name)

    }
}