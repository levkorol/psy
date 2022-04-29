package ru.harlion.psy.ui.exercise.base.edit.text_recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx
import java.time.LocalDate

class EditTextRecyclerViewModel : ViewModel() {

    private val repo = Repository.get()
    lateinit var exercise: LiveData<Exercise>

    fun getExById(id : Long) {
        exercise = repo.getExById(id)
    }

    fun add(
        fieldOne: String,
        list: List<String>,
        typeEx: TypeEx
    ) {
        val exercise = Exercise(
            fieldOne = fieldOne,
            listString = list,
            dateCreate = LocalDate.now().toEpochDay(),
            type = typeEx
        )
        repo.addExercise(exercise)
    }
}