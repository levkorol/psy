package ru.harlion.psy.models


class Exercise (
    val id : Long = 0,
    val dateCreate : Long = 0,
    val date : Long = 0,
    val name : String = "",
    val isDone : Boolean = false,
    val listString : List <String> = listOf(),
    val field_one : String = "",
    val field_two : String = "",
    val field_three : String = "",
    val field_for : String = "",
    val field_fife : String = "",
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
}
