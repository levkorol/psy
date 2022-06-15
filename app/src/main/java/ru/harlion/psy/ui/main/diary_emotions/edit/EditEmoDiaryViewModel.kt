package ru.harlion.psy.ui.main.diary_emotions.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.models.emotions.EmotionEvent

class EditEmoDiaryViewModel : ViewModel() {

    private val repo = Repository.get()

    lateinit var emotionEvent: LiveData<EmotionEvent>

    fun getEmoEventById(id: Long) {
        emotionEvent = repo.getEmoEventById(id)
    }

    fun delete(id: Long) {
        repo.deleteEmoEvent(id)
    }

    fun add(
        date: Long,
        time: Long,
        fieldOne: String,
        fieldTwo: String,
        fieldThree: String,
        fieldFor: String,
        checkedEmotions: HashSet<Emotion>
    ) {
        val emoEvent = EmotionEvent(
            date = date,
            time = time,
            fieldOne = fieldOne,
            fieldTwo = fieldTwo,
            fieldThree = fieldThree,
            fieldFor = fieldFor,
            emotions = checkedEmotions
        )
        repo.addEmotionEvent(emoEvent)
    }

    fun update(
        date: Long,
        time: Long,
        fieldOne: String,
        fieldTwo: String,
        fieldThree: String,
        fieldFor: String,
        checkedEmotions: HashSet<Emotion>
    ) {
        val emoEvent = EmotionEvent(
            id = emotionEvent.value?.id ?: 0L,
            date = date,
            time = time,
            fieldOne = fieldOne,
            fieldTwo = fieldTwo,
            fieldThree = fieldThree,
            fieldFor = fieldFor,
            emotions = checkedEmotions
        )
        repo.updateEmoEvent(emoEvent)
    }
}