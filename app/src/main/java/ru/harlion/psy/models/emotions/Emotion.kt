package ru.harlion.psy.models.emotions

import java.io.Serializable

class Emotion (
    val id: Long = 0,
    val name: String = "",
    val category: String = "",
    val level: Int = 0,
    val color: Int = 0
) : Serializable