package ru.harlion.psy.ui.main.my_day

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.Poll

class DayPollsViewModel : ViewModel() {

    private val repo = Repository.get()
    lateinit var polls: LiveData<List<Poll>>

    fun getListPolls() {
        polls = repo.getListPolls()
    }
}