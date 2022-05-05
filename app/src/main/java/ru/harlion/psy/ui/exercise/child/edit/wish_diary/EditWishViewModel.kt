package ru.harlion.psy.ui.exercise.child.edit.wish_diary

import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.base.BaseViewModel

class EditWishViewModel: BaseViewModel() {

    fun add(
        fieldOne : String,
        dateDone: Long,
        listSteps: List<String>,
    ){
        val exercise = Exercise(
            fieldOne = fieldOne,
            date = dateDone,
            dateCreate = System.currentTimeMillis(),
            listString = listSteps,
            type = TypeEx.WISH_DIARY
        )
        repo.addExercise(exercise)
    }

    fun update(
        fieldOne : String,
        dateDone: Long,
        listSteps: List<String>,
    ){
        val exercise = Exercise(
            id = exercise.value?.id ?: 0L,
            fieldOne = fieldOne,
            date = dateDone,
            listString = listSteps,
            dateCreate = exercise.value?.dateCreate ?: 0L,
            type = exercise.value?.type ?: TypeEx.NOTHING
        )
        repo.updateEx(exercise)
    }
}