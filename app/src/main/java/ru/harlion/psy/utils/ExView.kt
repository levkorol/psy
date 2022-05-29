package ru.harlion.psy.utils

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import ru.harlion.psy.R

fun ImageView.setRoundImage(url: Uri?, @DrawableRes id: Int) {
    ContextCompat.getDrawable(context, id)?.let {
        Picasso
            .get()
            .load(url)
            .transform(CropCircleTransformation())
            .fit()
            .placeholder(it)
            .centerCrop()
            .into(this)
    }
}

fun IntArray.assertSorted() : IntArray{
    val other = this.sortedArray()
    require(this.contentEquals(other)) {
        this.mapIndexed() { i, it ->
            val indexOf = other.indexOf(it)
            if(indexOf(it) != indexOf) {
                "$i must be $indexOf"
            } else {
                null
            }
        }.filterNotNull().joinToString()
    }
    return this
}