package ru.harlion.psy.ui.exercise.child.edit.highlights_album

import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.base.BaseViewModel

class EditAlbumViewModel : BaseViewModel() {

    fun add(
        fieldOne : String,
        dateCreate: Long,
        dateDone: Long,
        listSteps: List<String>,
    ){
        val exercise = Exercise(
            fieldOne = fieldOne,
            date = dateDone,
            dateCreate = dateCreate,
            listString = listSteps,
            type = TypeEx.WISH_DIARY
        )
        repo.addExercise(exercise)
    }

    fun update(
        fieldOne : String,
        dateCreate: Long,
        dateDone: Long,
        listSteps: List<String>,
    ){
        val exercise = Exercise(
            id = exercise.value?.id ?: 0L,
            fieldOne = fieldOne,
            date = dateDone,
            dateCreate = dateCreate,
            listString = listSteps,
            type = exercise.value?.type ?: TypeEx.NOTHING
        )
        repo.updateEx(exercise)
    }
}