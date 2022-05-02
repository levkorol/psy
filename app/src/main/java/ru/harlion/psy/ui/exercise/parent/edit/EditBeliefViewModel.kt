package ru.harlion.psy.ui.exercise.parent.edit

import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.ui.exercise.base.BaseViewModel

class EditBeliefViewModel : BaseViewModel() {

    fun add(
        fieldOne : String,
        dateCreate: Long,
        listSteps: List<String>,
        fieldTwo : String,
    ){
        val exercise = Exercise(
            fieldOne = fieldOne,
            listString = listSteps,
            fieldTwo = fieldTwo,
            dateCreate = dateCreate,
            type = TypeEx.WORK_WITH_BELIEFS
        )
        repo.addExercise(exercise)
    }

    fun update(
        fieldOne : String,
        dateCreate: Long,
        listSteps: List<String>,
        fieldTwo : String,
    ){
        val exercise = Exercise(
            id = exercise.value?.id ?: 0L,
            fieldOne = fieldOne,
            listString = listSteps,
            fieldTwo = fieldTwo,
            dateCreate = dateCreate,
            type = exercise.value?.type ?: TypeEx.NOTHING
        )
        repo.updateEx(exercise)
    }
}