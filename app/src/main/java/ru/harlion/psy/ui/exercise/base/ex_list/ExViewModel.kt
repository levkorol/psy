package ru.harlion.psy.ui.exercise.base.ex_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx

class ExViewModel : ViewModel() {

    private val repo = Repository.get()
    lateinit var exercises: List<LiveData<List<Exercise>>>

    fun getEx(typeEx: TypeEx, withArchive: Boolean) {
        val element = repo.getExListByArchive(typeEx, false)
        val elementArchive = repo.getExListByArchive(typeEx, true)
        exercises = if (withArchive) {
            listOf(element, elementArchive)
        } else {
            listOf(element)
        }
    }
}