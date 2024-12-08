package com.example.onlineshopping.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopping.R
import com.example.onlineshopping.databinding.ViewholderModelBinding

class SelectModelAdapter(private val items:MutableList<String>): RecyclerView.Adapter<SelectModelAdapter.Viewholder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

   inner class Viewholder(val binding:ViewholderModelBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectModelAdapter.Viewholder {
        context = parent.context
        val binding=ViewholderModelBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: SelectModelAdapter.Viewholder, position: Int) {
            holder.binding.modelTxt.text = items[position]
        holder.binding.root.setOnClickListener {
            lastSelectedPosition= selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if(selectedPosition==position) {
            holder.binding.modelLayout.setBackgroundResource(R.drawable.green_bg_selected)
            val color = ContextCompat.getColor(context, R.color.green)
            holder.binding.modelTxt.setTextColor(color)
        } else {
            holder.binding.modelLayout.setBackgroundResource(R.drawable.grey_bg)
            val color = ContextCompat.getColor(context, R.color.black)
            holder.binding.modelTxt.setTextColor(color)
        }

    }

    override fun getItemCount(): Int = items.size
}