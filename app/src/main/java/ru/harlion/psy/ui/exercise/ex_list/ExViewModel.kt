package ru.harlion.psy.ui.exercise.ex_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx

class ExViewModel: ViewModel() {

    private val repo = Repository.get()
    lateinit var exercises : List<LiveData<List<Exercise>>>

    fun getEx(typeEx : TypeEx, isArchive : Boolean) {
        val element = repo.getExList(typeEx)
        exercises = if(isArchive) {
            listOf(element, element)
        } else {
            listOf(element)
        }
    }
}