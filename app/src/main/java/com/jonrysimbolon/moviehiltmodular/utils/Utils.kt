package com.jonrysimbolon.moviehiltmodular.utils

import android.widget.ImageView
import com.jonrysimbolon.core.utils.setImageUrl
import com.jonrysimbolon.moviehiltmodular.R

fun setImageUrlDefault(
    url: String,
    imageView: ImageView,
) {
    setImageUrl(
        url = url,
        imageView = imageView,
        errorImage = R.drawable.notfound,
        placeHolderImage = R.drawable.cinema
    )
}

fun setImageDefaultWithRadius(
    url: String,
    imageView: ImageView,
    radius: Float
) {
    setImageUrl(
        url = url,
        imageView = imageView,
        errorImage = R.drawable.notfound,
        placeHolderImage = R.drawable.cinema,
        roundedEdge = true,
        topLeft = radius,
        topRight = radius,
        bottomLeft = radius,
        bottomRight = radius,
    )
}