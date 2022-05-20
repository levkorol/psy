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
    var emotions: List<Emotion>? = null

    fun getEmoEventById(id: Long) {
        emotionEvent = repo.getEmoEventById(id)
    }

    fun delete(id: Long) {
        repo.deleteEx(id)
    }

    fun add(
        date: Long,
        time: Long,
        fieldOne: String,
        fieldTwo: String,
        fieldThree: String,
        fieldFor: String,
        listEmotions: List<String>
    ) {
        val emoEvent = EmotionEvent(
            date = date,
            time = time,
            fieldOne = fieldOne,
            fieldTwo = fieldTwo,
            fieldThree = fieldThree,
            fieldFor = fieldFor,
            emotions = listEmotions
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
        listEmotions: List<String>
    ) {
        val emoEvent = EmotionEvent(
            id = emotionEvent.value?.id ?: 0L,
            date = date,
            time = time,
            fieldOne = fieldOne,
            fieldTwo = fieldTwo,
            fieldThree = fieldThree,
            fieldFor = fieldFor,
            emotions = listEmotions
        )
        repo.updateEmoEvent(emoEvent)
    }
}