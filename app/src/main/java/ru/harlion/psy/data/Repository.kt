package ru.harlion.psy.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.Poll
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.models.emotions.EmotionEvent

private const val DATABASE_NAME = "data"

class Repository private constructor(context: Context) {

    private val database: DataBaseApp = Room.databaseBuilder(
        context.applicationContext,
        DataBaseApp::class.java,
        DATABASE_NAME
    ).allowMainThreadQueries()
        .build()

    private val exerciseDao = database.exerciseDao()
    private val emotionEventDao = database.emotionEventDao()
    private val pollDao = database.pollDao()

    /**
     * Poll
     */

    fun addPoll(poll: Poll) {
        pollDao.add(poll)
    }

    fun getListPolls(): LiveData<List<Poll>> = pollDao.getLists()

    fun getPollById(id: Long) = pollDao.pollById(id)


    fun updatePoll(poll: Poll) {
        pollDao.update(poll)
    }

    fun deletePoll() {}


    /**
     * EmotionEvent
     */
    fun addEmotionEvent(emotionEvent: EmotionEvent) {
        emotionEventDao.add(emotionEvent)
    }

    fun updateEmoEvent(emotionEvent: EmotionEvent) {
        emotionEventDao.update(emotionEvent)
    }

    fun getEmoEventsList(): LiveData<List<EmotionEvent>> {
        return emotionEventDao.getLists()
    }

    fun getEmoEventById(id: Long) = emotionEventDao.emotionEventById(id)

    fun deleteEmoEvent(id: Long) {
        emotionEventDao.deleteById(id)
    }

    /**
     * Exercises
     */

    fun addExercise(exercise: Exercise) {
        exerciseDao.add(exercise)
    }

    fun getExList(typeEx: TypeEx): LiveData<List<Exercise>> {
        return exerciseDao.getListsLiveData(typeEx)
    }

    fun getListEx(typeEx: TypeEx): List<Exercise> {
        return exerciseDao.getLists(typeEx)
    }

    fun getExListByArchive(typeEx: TypeEx, isArchive: Boolean): LiveData<List<Exercise>> {
        return exerciseDao.getListsByArchive(typeEx, isArchive)
    }

    fun <E : Exercise?> getExById(id: Long): LiveData<E> = exerciseDao.exById(id) as LiveData<E>

    fun updateEx(exercise: Exercise) {
        exerciseDao.update(exercise)
    }

    fun deleteEx(id: Long) {
        exerciseDao.deleteById(id)
    }

    fun updateIsArchiveEx(id: Long, isArchive: Boolean) {
        exerciseDao.updateFields(id, isArchive = isArchive)
    }

    companion object {
        private var INSTANCE: Repository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = Repository(context)
            }
        }

        fun get(): Repository {
            return INSTANCE ?: throw IllegalStateException("Repository must be init")
        }

    }
}