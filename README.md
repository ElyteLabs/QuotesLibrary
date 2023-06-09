# QuotesLibrary
Quotes Library Utils and Helpers

## Description
This project demonstrates a simple Android application that allows users to customize the layout, font, and color of a text view. 
Users can select different backgrounds, fonts, and colors, and perform actions such as sharing the text or opening a web page.

## Installation
To use this project, follow these steps:
Step 1. Add the JitPack repository to your build file
 Add it in your root build.gradle at the end of repositories:
 
     allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	  }
       
 Step 2. Add the dependency
 
      dependencies {
	        implementation 'com.github.ElyteLabs:QuotesLibrary:1.2'
	}

## Usage
In your activity or fragment, declare the following views:
#### ColorGenerator - 
Library for generating random colors.
##### Usage
     ColorGenerator.getRandomColor() //Generates a random color
     getColorList() // Reurns list of colors
#### ImageSelectorDialog - 
Custom dialog for selecting images and colors.
##### Usage
    val selectorDialog = ImageSelectorDialog(this)
            selectorDialog.setBackgroundsList(backgrounds = backgrounds) // takes List<Int> Backgrounds
            selectorDialog.setImageSelectedListener(object :ImageSelectorDialog.ImagePickerListener{
                override fun onImageSelected(imageResource: Int) {
                   
                }

                override fun onColorSelected(color: Int) {
                   
                }

            })
            selectorDialog.showImageSelectionDialog()

#### FontStyleDialog - 
Custom dialog for selecting fonts.
##### Usage
     val styleDialog = FontStyleDialog(this)
            styleDialog.setFontsList(fonts = fonts)
            styleDialog.setFontSelectedListener(object :FontStyleDialog.FontPickerListener{
                override fun onFontSelected(font: Int) {
                  textView.typeface = ResourcesCompat.getFont(this@MainActivity, font)
                }

            })
            styleDialog.showFontSelectionDialog()
            
#### ColorPickerDialog 
- Custom dialog for selecting colors.
##### Usage
      val colorPickerDialog = ColorPickerDialog(this)
            colorPickerDialog.setColorSelectedListener(object :ColorPickerDialog.ColorPickerListener{
                override fun onColorSelected(color: Int) {
                    rootLayout.setBackgroundColor(color)
                }

            })
            colorPickerDialog.showColorPickerDialog()
#### UtilsHelper - 
Helper methods for common tasks.
##### Usage
    UtilsHelper.shareText(this,textView.text.toString())
    UtilsHelper.copyText(this,textView.text.toString())
    UtilsHelper.openWebPage(this,WEBSITE_URL)
    UtilsHelper.showRatingDialog(this) // InAppReviewManager 
    
    
### License
This project is licensed under the[ Apache 2.0 ](http://www.apache.org/licenses/LICENSE-2.0.txt)License.
