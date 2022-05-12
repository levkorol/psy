package ru.harlion.psy.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class Poll(
    val id : Long,
    val dateCreate: Long,
    val question: List<Answer>
)

@Parcelize
class Answer(
    val position: Int,
    var assessment: Int,
    var comment : String
) : Parcelable

