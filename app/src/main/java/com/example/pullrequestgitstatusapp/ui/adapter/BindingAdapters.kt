package com.example.pullrequestgitstatusapp.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.StringSignature
import com.example.pullrequestgitstatusapp.R

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("imageUrl")
fun bindLoadImage(image: ImageView, url: String?) {
    if (!url.isNullOrEmpty())
        Glide.with(image.context.applicationContext)
                .load(url)
                .signature(StringSignature(url))
                .dontAnimate()
                .placeholder(R.drawable.ic_image)
                .into(image)
}


