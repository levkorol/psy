package ru.harlion.psy.ui.exercise.child.edit.free_writing

import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.base.BaseViewModel

class EditFreeWritingViewModel : BaseViewModel() {

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
            type = TypeEx.FREE_WRITING
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