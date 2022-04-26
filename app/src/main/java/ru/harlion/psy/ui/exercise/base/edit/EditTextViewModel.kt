package ru.harlion.psy.ui.exercise.base.edit

import androidx.lifecycle.ViewModel
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx
import java.time.LocalDate

class EditTextViewModel: ViewModel() {

    private val repo = Repository.get()

    fun addEx(
        fieldOne : String,
        fieldTwo : String,
        fieldThree : String = "",
        fieldFor : String = "",
        typeEx : TypeEx
    ) {
        val exercise = Exercise(
            dateCreate = LocalDate.now().toEpochDay(),
            fieldOne = fieldOne,
            fieldTwo = fieldTwo,
            fieldThree = fieldThree,
            fieldFor = fieldFor,
            type = typeEx
        )

        repo.addExercise(exercise)
    }
}