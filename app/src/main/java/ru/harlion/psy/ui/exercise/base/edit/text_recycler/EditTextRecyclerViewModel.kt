package ru.harlion.psy.ui.exercise.base.edit.text_recycler

import androidx.lifecycle.ViewModel
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx
import java.time.LocalDate

class EditTextRecyclerViewModel : ViewModel() {

    private val repo = Repository.get()

    fun add(
        name: String,
        list: List<String>,
        typeEx: TypeEx
    ) {
        val exercise = Exercise(
            name = name,
            listString = list,
            dateCreate = LocalDate.now().toEpochDay(),
            type = typeEx
        )
        repo.addExercise(exercise)
    }
}