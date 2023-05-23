package com.elytelabs.quoteutils.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elytelabs.quoteutils.R

class ColorPickerDialog (private val context: Context) {

    private val colors = listOf(
        0xffe57373.toInt(),
        0xfff06292.toInt(),
        0xffba68c8.toInt(),
        0xff9575cd.toInt(),
        0xff7986cb.toInt(),
        0xff64b5f6.toInt(),
        0xff4fc3f7.toInt(),
        0xff4dd0e1.toInt(),
        0xff4db6ac.toInt(),
        0xff81c784.toInt(),
        0xffaed581.toInt(),
        0xffff8a65.toInt(),
        0xffd4e157.toInt(),
        0xffffd54f.toInt(),
        0xffffb74d.toInt(),
        0xffa1887f.toInt(),
        0xff90a4ae.toInt()
    )

    // TODO: Add more colors https://htmlcolorcodes.com/color-chart/material-design-color-chart/
    private var colorPickerListener: ColorPickerListener? = null

    fun setColorSelectedListener(listener: ColorPickerListener) {
        this.colorPickerListener = listener
    }

    interface ColorPickerListener {
        fun onColorSelected(color: Int)
    }

    fun showColorPickerDialog() {
        val dialog = Dialog(context)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_color_picker, null)
        dialog.setContentView(dialogView)
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.colorRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 4)
        val adapter = ColorAdapter()
        recyclerView.adapter = adapter

        dialogView.findViewById<ImageView>(R.id.colorBackButton).setOnClickListener {
            dialog.dismiss()
        }

        dialogView.findViewById<TextView>(R.id.closeDialog).setOnClickListener {
            dialog.dismiss()
        }

        adapter.setOnItemClickListener { color ->
            // Set the selected color as the background of the parent layout
            colorPickerListener?.onColorSelected(color)
            dialog.dismiss()
        }

        adapter.setColors(colors)

        dialog.show()
    }

    private inner class ColorAdapter : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

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
}