package com.elytelabs.quoteslibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.elytelabs.quoteutils.dialogs.ColorPickerDialog
import com.elytelabs.quoteutils.dialogs.FontStyleDialog
import com.elytelabs.quoteutils.dialogs.ImageSelectorDialog
import com.elytelabs.quoteutils.utils.ColorGenerator
import com.elytelabs.quoteutils.utils.PrefUtils
import com.elytelabs.quoteutils.utils.UtilsHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      val WEBSITE_URL = "https://elytelabs.blogspot.com/"

        val backgrounds = listOf(R.drawable.bg11, R.drawable.bg22, R.drawable.bg23, R.drawable.bg25, R.drawable.bg5)

        val fonts = listOf(R.font.righteous, R.font.salsa, R.font.schoolbell, R.font.sofadi_one)

        val rootLayout: RelativeLayout = findViewById(R.id.rootLayout)
        val textView: TextView = findViewById(R.id.textView)
        val btnImageSelector: Button = findViewById(R.id.btnImageSelector)
        val btnFontSelector: Button = findViewById(R.id.btnFontSelector)
        val btnColorSelector: Button = findViewById(R.id.btnColorSelector)
        val btnShare: Button = findViewById(R.id.btnShare)
        val btnCopy: Button = findViewById(R.id.btnCopy)
        val btnUrl: Button = findViewById(R.id.btnUrl)
        val randomColor: Button = findViewById(R.id.randomColor)

        btnImageSelector.setOnClickListener {
           val selectorDialog = ImageSelectorDialog(this)
            selectorDialog.setBackgroundsList(backgrounds = backgrounds)
            selectorDialog.setImageSelectedListener(object :ImageSelectorDialog.ImagePickerListener{
                override fun onImageSelected(imageResource: Int) {
                    rootLayout.setBackgroundResource(imageResource)
                }

            })
            selectorDialog.showImageSelectionDialog()
        }
        btnFontSelector.setOnClickListener {
            val styleDialog = FontStyleDialog(this)
            styleDialog.setFontsList(fonts = fonts)
            styleDialog.setFontSelectedListener(object :FontStyleDialog.FontPickerListener{
                override fun onFontSelected(font: Int) {
                  textView.typeface = ResourcesCompat.getFont(this@MainActivity, font)
                }

            })
            styleDialog.showFontSelectionDialog()
        }
        btnColorSelector.setOnClickListener {
            val colorPickerDialog = ColorPickerDialog(this)
            colorPickerDialog.setColorSelectedListener(object :ColorPickerDialog.ColorPickerListener{
                override fun onColorSelected(color: Int) {
                    rootLayout.setBackgroundColor(color)
                }

            })
            colorPickerDialog.showColorPickerDialog()
        }

        randomColor.setOnClickListener {
           val color = ColorGenerator.DEFAULT.getRandomColor()
            rootLayout.setBackgroundColor(color)
        }

        btnShare.setOnClickListener {
            UtilsHelper.shareText(this,textView.text.toString())
        }
        btnCopy.setOnClickListener {
            UtilsHelper.copyText(this,textView.text.toString())
        }
        btnUrl.setOnClickListener {
            UtilsHelper.openWebPage(this,WEBSITE_URL)
        }
    }
}