package ru.harlion.psy.models.user

data class User(
    val photoMain: String,
    val name: String,
    val progressChild: Int,
    val photoChild: String,
    val nameChild: String,
    val progressAdult: Int,
    val photoAdult: String,
    val nameAdult: String,
    val progressParent: Int,
    val photoParent: String,
    val nameParent: String,
) {
    companion object {
        val emptyUser = User(
            "",
            "",
            0,
            "",
            "",
            0,
            "",
            "",
            0,
            "",
            ""
        )
    }
}

