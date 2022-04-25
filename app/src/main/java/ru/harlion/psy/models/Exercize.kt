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

}
