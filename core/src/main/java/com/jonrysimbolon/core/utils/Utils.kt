package com.jonrysimbolon.core.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.gson.GsonBuilder
import com.jonrysimbolon.core.model.ErrorModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun responseGsonPattern(json: String?): ErrorModel =
    GsonBuilder()
        .create()
        .fromJson(json, ErrorModel::class.java)

fun String.withDateSimpleFormat(): String {
    val inputFormat = SimpleDateFormat(dateSimpleFormatFromServer, Locale.getDefault())
    val outputFormat =
        DateFormat.getDateInstance(DateFormat.MEDIUM)
    val date: Date = inputFormat.parse(this) ?: return ""
    return outputFormat.format(date)
}

fun String.withDateLongFormat(): String {
    val inputFormat = SimpleDateFormat(dateLongFormatFromServer, Locale.getDefault())
    val outputFormat =
        DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT, Locale.getDefault())
    val date: Date = inputFormat.parse(this) ?: return ""
    return outputFormat.format(date)
}

fun setImageUrl(
    url: String,
    imageView: ImageView,
    crossFade: Boolean = true,
    @DrawableRes
    placeHolderImage: Int,
    @DrawableRes
    errorImage: Int,
    roundedEdge: Boolean = false,
    topLeft: Float = 0f,
    topRight: Float = 0f,
    bottomLeft: Float = 0f,
    bottomRight: Float = 0f,
) {
    val urlPath = image_path.plus(url)
    imageView.load(urlPath) {
        crossfade(crossFade)
        placeholder(placeHolderImage)
        error(errorImage)
        if (roundedEdge) {
            transformations(
                RoundedCornersTransformation(
                    topLeft,
                    topRight,
                    bottomLeft,
                    bottomRight
                )
            )
        }
    }
}