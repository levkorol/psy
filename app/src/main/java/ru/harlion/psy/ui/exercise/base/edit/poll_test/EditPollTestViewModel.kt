package ru.harlion.psy.ui.exercise.base.edit.poll_test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.Answer
import ru.harlion.psy.models.Poll

class EditPollTestViewModel : ViewModel() {

    private val repo = Repository.get()
    lateinit var poll : LiveData<Poll>

    fun getPollById(id : Long) {
      poll = repo.getPollById(id)
    }

    fun add(
        questions: List<Answer>
    ) {
        val poll = Poll(
            dateCreate = System.currentTimeMillis(),
            question = questions
        )
        repo.addPoll(poll)
    }

}