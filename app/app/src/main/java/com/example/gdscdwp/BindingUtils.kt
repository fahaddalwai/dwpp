package com.example.gdscdwp

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView?, imgUrl: String?) {

    imgUrl?.let {
        val imgUri =
            imgUrl.toUri().buildUpon().scheme("https").build()
        imgView?.let {
            Glide.with(imgView.context)
                .load(imgUri)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .centerCrop()
                .dontTransform()
                .priority(Priority.IMMEDIATE)
                .format(DecodeFormat.DEFAULT)
                .into(imgView)
        }

    }
}
