package ru.harlion.psy.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
class Poll(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val dateCreate: Long,
    val question: List<Answer>
)

@Parcelize
class Answer(
    val position: Int,
    var assessment: Int,
    var comment : String
) : Parcelable

