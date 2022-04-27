package ru.harlion.psy.ui.exercise.ex_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx

class ExViewModel: ViewModel() {

    private val repo = Repository.get()
    var exercises = MutableLiveData<List<Exercise>>()

    fun getEx(typeEx : TypeEx) {
        exercises.value = repo.getExList(typeEx)
    }
}