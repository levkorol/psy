package ru.harlion.psy.ui.exercise.base.edit.text_recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx
import java.time.LocalDate

class EditTextRecyclerViewModel : ViewModel() {

    private val repo = Repository.get()
    lateinit var exercise: LiveData<Exercise>

    fun getExById(id: Long) {
        exercise = repo.getExById(id)
    }

    fun update(
        fieldOne: String,
        list: List<String>
    ) {
        val newExercise = Exercise(
            id = exercise.value?.id ?: 0L,
            fieldOne = fieldOne,
            listString = list,
            dateCreate = exercise.value?.dateCreate ?: 0L,
            type = exercise.value?.type ?: TypeEx.NOTHING
        )
        repo.updateEx(newExercise)
    }

    fun add(
        fieldOne: String,
        list: List<String>,
        typeEx: TypeEx
    ) {
        val exercise = Exercise(
            fieldOne = fieldOne,
            listString = list,
            dateCreate = System.currentTimeMillis(),
            type = typeEx
        )
        repo.addExercise(exercise)
    }

    fun delete(id: Long) {
        repo.deleteEx(id)
    }
}