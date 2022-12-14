package com.example.glidetest

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

object BottomGlideUtils {
    fun load(context: Context, bitmap: Bitmap, appCompatImageView: AppCompatImageView) {
        try {
            Log.d("Glide", "Load bitmap: $bitmap")
            Glide.with(context)
                .load(bitmap)
                .override(bitmap.width, bitmap.height)
                // There's a blink issue without this placeholder, this is a workaround but unfortunately it's unsafe per https://github.com/bumptech/glide/issues/527
                // But I didn't find a better way to avoid the blink, I choose to leave it here since it works well during my test and add a fallback workaround in catch block when it happens
                .placeholder(appCompatImageView.drawable)
                .into(appCompatImageView)
        } catch (t: Throwable) {
            Log.e("Glide", "Caught throwable when loading bitmap $bitmap, exception: $t, fallback to the solution without placeholder")
            Glide.with(context)
                .load(bitmap)
                .override(bitmap.width, bitmap.height)
                .into(appCompatImageView)
        }
    }
}