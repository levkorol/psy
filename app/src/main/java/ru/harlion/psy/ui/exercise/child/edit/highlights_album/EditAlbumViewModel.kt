package ru.harlion.psy.ui.exercise.child.edit.highlights_album

import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.base.BaseViewModel
import java.time.LocalDate

class EditAlbumViewModel : BaseViewModel() {

    fun add(
        fieldOne : String,
        fieldTwo : String,
        image : String,
        dateCreate: Long
    ){
        val exercise = Exercise(
            fieldOne = fieldOne,
            fieldTwo= fieldTwo,
            image = image,
            date = dateCreate,
            dateCreate = System.currentTimeMillis(),
            type = TypeEx.HIGHLIGHTS_ALBUM
        )
        repo.addExercise(exercise)
    }

    fun update(
        fieldOne : String,
        fieldTwo : String,
        image : String,
        dateCreate: Long
    ){
        val exercise = Exercise(
            id = exercise.value?.id ?: 0L,
            fieldOne = fieldOne,
            fieldTwo= fieldTwo,
            image = image,
            date = dateCreate,
            dateCreate = System.currentTimeMillis(),
            type = exercise.value?.type ?: TypeEx.NOTHING
        )
        repo.updateEx(exercise)
    }
}