package ru.harlion.psy.models.emotions

import androidx.room.Entity
import androidx.room.PrimaryKey

class Emotion (
    val id: Long = 0,
    val name: String = "", //описание
    val category: String = "",
    val level: Int = 0, //max 10
    val color: Int = 0,
    val isFav: Boolean = false
)