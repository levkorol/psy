package ru.harlion.psy.models.emotions

class Emotion (
    val id: Long = 0,
    val name: String = "", //описание
    val category: String = "",
    val level: Int = 0, //max 10
    val color: Int = 0,
    val isFav: Boolean = false
)