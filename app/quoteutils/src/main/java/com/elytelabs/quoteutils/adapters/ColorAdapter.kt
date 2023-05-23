package com.elytelabs.quoteutils.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elytelabs.quoteutils.R

class ColorAdapter : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    private var colors: List<Int> = emptyList()
    private var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_color,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val color = colors[position]
        holder.colorView.setBackgroundColor(color)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(color)
        }
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    fun setColors(colors: List<Int>) {
        this.colors = colors
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        this.onItemClickListener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorView: View = itemView.findViewById(R.id.colorView)
    }
}