package ru.harlion.psy.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Exercise (
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val dateCreate : Long = 0,
    val date : Long = 0,
    val isArchive : Boolean = false,
    val listString : List <String> = listOf(),
    val fieldOne : String = "",
    val fieldTwo : String = "",
    val fieldThree : String = "",
    val fieldFor : String = "",
    val fieldFife : String = "",
    val image : String = "",
    val type : TypeEx
)

enum class TypeEx {
    IDEAS_DIARY,
    WISH_DIARY,
    FREE_WRITING,
    HIGHLIGHTS_ALBUM,
    GRATITUDE_DIARY,

    SELF_ESTEEM,
    FAIL_DIARY,
    ACTS_SELF_LOVE,
    MY_AMBULANCE,

    PERFECT_LIFE,
    SUCCESS_DIARY,
    WORK_WITH_BELIEFS,
    LIFE_RULES,
    POSITIVE_BELIEFS,

    NOTHING
}
