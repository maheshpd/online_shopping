package com.example.onlineshopping.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshopping.R
import com.example.onlineshopping.databinding.ViewholderPicBinding

class PicAdapter(private val items:MutableList<String>, private val onImageSelected:(String)->Unit):
    RecyclerView.Adapter<PicAdapter.ViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    class ViewHolder(val binding: ViewholderPicBinding):RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ViewholderPicBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.pic.loadImage(item)
        if(selectedPosition==position) {
            holder.binding.picLayout.setBackgroundResource(R.drawable.green_bg_selected)
        } else {
            holder.binding.picLayout.setBackgroundResource(R.drawable.grey_bg)
        }
        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            onImageSelected(item)


        }

    }

    override fun getItemCount(): Int = items.size

    fun ImageView.loadImage(url:String) {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}