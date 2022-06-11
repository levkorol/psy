package ru.harlion.psy.models.emotions

import android.content.res.Resources
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import java.io.Serializable

data class CategoryEmotions(
    val id : Int,
    val emoji: Int,
    @StringRes
    val name: Int,
    @ColorRes
    val color: Int,
    @ArrayRes
    val emotions: Int
) {
    fun getName(res: Resources) = String(Character.toChars(emoji)) + "  " + res.getString(name)

    fun getEmotions(res: Resources) = res.getStringArray(emotions).mapIndexed { index, _ ->
        Emotion(this, index)
    }
}


data class Emotion(
    val categoryEmotions: CategoryEmotions,
    val indexCat: Int
) : Serializable {
    fun getName(res: Resources) = res.getStringArray(categoryEmotions.emotions)[indexCat]
}