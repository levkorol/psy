package ru.harlion.psy.ui.main.diary_emotions

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.emotions.EmotionEvent

class DiaryEmotionsViewModel: ViewModel() {

    private val repo = Repository.get()
    lateinit var emoEvents: LiveData<List<EmotionEvent>>

    fun getListEmoEvents() {
        emoEvents = repo.getEmoEventsList()
    }
}