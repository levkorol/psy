package ru.harlion.psy.ui.exercise.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.Exercise

open class BaseViewModel : ViewModel() {

    protected val repo = Repository.get()
    lateinit var exercise: LiveData<Exercise>

    fun getExById(id : Long) {
        exercise = repo.getExById(id)
    }

    fun delete(id : Long){
        repo.deleteEx(id)
    }
}