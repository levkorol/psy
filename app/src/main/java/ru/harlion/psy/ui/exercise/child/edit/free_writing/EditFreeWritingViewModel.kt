package ru.harlion.psy.ui.exercise.child.edit.free_writing

import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.base.BaseViewModel

class EditFreeWritingViewModel : BaseViewModel() {

    fun add(
        fieldOne: String,
        fieldTwo: String,
        fieldThree: String,
    ) {
        val exercise = Exercise(
            fieldOne = fieldOne,
            fieldTwo = fieldTwo,
            fieldThree = fieldThree,
            dateCreate = System.currentTimeMillis(),
            type = TypeEx.FREE_WRITING
        )
        repo.addExercise(exercise)
    }

    fun update(
        fieldOne: String,
        fieldTwo: String,
        fieldThree: String
    ) {
        val exercise = Exercise(
            id = exercise.value?.id ?: 0L,
            fieldOne = fieldOne,
            fieldTwo = fieldTwo,
            fieldThree = fieldThree,
            dateCreate = exercise.value?.dateCreate ?: 0L,
            type = exercise.value?.type ?: TypeEx.NOTHING
        )
        repo.updateEx(exercise)
    }
}