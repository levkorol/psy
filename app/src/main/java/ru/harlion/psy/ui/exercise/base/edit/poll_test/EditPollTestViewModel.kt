package ru.harlion.psy.ui.exercise.base.edit.poll_test

import androidx.lifecycle.LiveData
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

    fun update(
        answer: List<Answer>
    ) {
        val poll = Poll(
            id = poll.value?.id ?: 0L,
            question = answer,
            dateCreate = poll.value?.dateCreate ?: System.currentTimeMillis()
        )
        repo.updatePoll(poll)
    }

    fun add(
        answer: List<Answer>
    ) {
        val poll = Poll(
            dateCreate = System.currentTimeMillis(),
            question = answer
        )
        repo.addPoll(poll)
    }

}