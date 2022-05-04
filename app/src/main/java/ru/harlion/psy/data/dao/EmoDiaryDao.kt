package ru.harlion.psy.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.harlion.psy.models.emotions.EmotionEvent

@Dao
abstract class EmoDiaryDao {

    @Insert
    abstract fun add(emotionEvent: EmotionEvent)

    @Update
    abstract fun update(emotionEvent: EmotionEvent)

    @Query("DELETE FROM emotionEvent WHERE id = :id")
    abstract fun deleteById(id : Long)

    @Query("SELECT * FROM emotionEvent")
    abstract fun getLists(): LiveData<List<EmotionEvent>>

    @Query("SELECT * FROM emotionEvent WHERE id = (:id)")
    abstract fun emotionEventById(id: Long): LiveData<EmotionEvent>
}