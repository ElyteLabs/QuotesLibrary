package com.elytelabs.quoteutils.dialogs

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elytelabs.quoteutils.R
import com.elytelabs.quoteutils.adapters.ColorAdapter
import com.elytelabs.quoteutils.utils.ColorGenerator

class ColorPickerDialog(private val context: Context) {

    private var colorPickerListener: ColorPickerListener? = null

    fun setColorSelectedListener(listener: ColorPickerListener) {
        this.colorPickerListener = listener
    }

    interface ColorPickerListener {
        fun onColorSelected(color: Int)
    }

    fun showColorPickerDialog() {

        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_color_picker, null)
        val builder = AlertDialog.Builder(context)
        builder.setView(dialogView)

        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.colorRecyclerView)
        val transparencySeekBar = dialogView.findViewById<SeekBar>(R.id.transparencySeekBar)
        recyclerView.layoutManager = GridLayoutManager(context, 5)
        val adapter = ColorAdapter()
        recyclerView.adapter = adapter

        val dialog = builder.show()

        dialogView.findViewById<ImageView>(R.id.colorBackButton).setOnClickListener {
            dialog.dismiss()
        }

        adapter.setOnItemClickListener { color ->
            val transparency = transparencySeekBar.progress
            val transparentColor = Color.argb(transparency, Color.red(color), Color.green(color), Color.blue(color))
            colorPickerListener?.onColorSelected(transparentColor)
            dialog.dismiss()
        }

        adapter.setColors(ColorGenerator.getColorList())

        transparencySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                       adapter.setTransparency(progress)
                       adapter.notifyDataSetChanged()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}