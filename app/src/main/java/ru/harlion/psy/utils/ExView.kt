package ru.harlion.psy.utils

import android.net.Uri
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import ru.harlion.psy.R

fun ImageView.setRoundImage(url: Uri?) {
    ContextCompat.getDrawable(context, R.drawable.ic_profile)?.let {
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