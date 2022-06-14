package ru.harlion.psy.models.emotions

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class EmotionEvent (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Long = 0,
    val time: Long = 0,
    val fieldOne: String = "",
    val fieldTwo: String = "",
    val fieldThree: String = "",
    val fieldFor: String = "",
    val fieldFife: String = "",
    val fieldSix: String = "",
    val fieldSeven: String = "",
    val fieldEight: String = "",
    val emotions: HashSet<Emotion> = hashSetOf()
)