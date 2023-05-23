package com.elytelabs.quoteutils.utils

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import com.elytelabs.quoteutils.R

object UtilsHelper {

    fun shareText(context: Context, value: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, value)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share via")
        context.startActivity(shareIntent)
    }

    fun showRatingDialog(activity: Activity) {
        InAppReviewManager.with(activity)
            .setInstallDays(2)
            .setLaunchTimes(3)
            .setRemindInterval(2)
            .monitor()
        // Show a dialog if meets conditions
        InAppReviewManager.showRateDialogIfNeeded(activity)
    }

    fun copyText(context: Context, value:String) {
        val clipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        // When setting the clipboard text.
        clipboardManager.setPrimaryClip(ClipData.newPlainText   ("text", value))
        // Only show a toast for Android 12 and lower.
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2)
            context.showToast("Copied to Clipboard")
    }

    fun openWebPage(context: Context, url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }


    private fun Context.showToast(message: String) =
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}