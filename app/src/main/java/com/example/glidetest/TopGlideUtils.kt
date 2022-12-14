package com.example.glidetest

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition

object TopGlideUtils {
    var oldImage: Bitmap? = null
    fun load(context: Context, bitmap: Bitmap, appCompatImageView: AppCompatImageView) {
        Glide.with(context)
            .load(bitmap)
            .thumbnail(
                Glide.with(context).load(oldImage).fitCenter()
            )
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    if (isFirstResource) return false
                    return DrawableCrossFadeFactory.Builder()
                        .build()
                        .build(DataSource.MEMORY_CACHE, false)
                        .transition(BitmapDrawable(context.resources, oldImage), target as Transition.ViewAdapter)
                }
            })
            .fitCenter()
            .into(appCompatImageView)
        oldImage = bitmap
    }
}