package ru.harlion.psy.ui.exercise.base.edit.edit_text

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx
import java.time.LocalDate

class EditTextViewModel : ViewModel() {

    private val repo = Repository.get()
    lateinit var exercise: LiveData<Exercise>

    fun getExById(id: Long) {
        exercise = repo.getExById(id)
    }

    fun updateTask(
        fieldOne: String,
        fieldTwo: String,
        fieldThree: String = "",
        fieldFor: String = ""
    ) {
        val newExercise = Exercise(
            id = exercise.value?.id ?: 0L,
            fieldOne = fieldOne,
            fieldTwo = fieldTwo,
            fieldThree = fieldThree,
            fieldFor = fieldFor,
            dateCreate = exercise.value?.dateCreate ?: 0L,
            type = exercise.value?.type ?: TypeEx.NOTHING
        )
        repo.updateEx(newExercise)
    }

    fun addEx(
        fieldOne: String,
        fieldTwo: String,
        fieldThree: String = "",
        fieldFor: String = "",
        typeEx: TypeEx
    ) {
        val exercise = Exercise(
            dateCreate = System.currentTimeMillis(),
            fieldOne = fieldOne,
            fieldTwo = fieldTwo,
            fieldThree = fieldThree,
            fieldFor = fieldFor,
            type = typeEx
        )

        repo.addExercise(exercise)
    }

    fun deleteEx(id: Long) {
        repo.deleteEx(id)
    }
}