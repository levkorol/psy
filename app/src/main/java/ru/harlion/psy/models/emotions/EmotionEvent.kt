package ru.harlion.psy.models.emotions

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class EmotionEvent (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var date: Long = 0,
    var time: Long = 0,
    var fieldOne: String = "",
    var fieldTwo: String = "",
    var fieldThree: String = "",
    var fieldFor: String = "",
    var fieldFife: String = "",
    var fieldSix: String = "",
    var fieldSeven: String = "",
    var fieldEight: String = "",
    var emotions: HashSet<Emotion> = hashSetOf()
)