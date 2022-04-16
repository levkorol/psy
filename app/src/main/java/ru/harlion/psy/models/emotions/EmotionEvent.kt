package ru.harlion.psy.models.emotions

class EmotionEvent (
    val id: Long = -1,
    val date: Long = 0,
    val people: String = "",
    val place: String = "",
    val name: String = "", //название
    val description: String= "", //описание
    val emotion: List<Emotion> = listOf(), // емоции чувства ощущения
    val label: String = ""
)