package ru.harlion.psy.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.emotions.Emotion
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

    fun addExercise(exercise: Exercise) {
        exerciseDao.add(exercise)
    }

    fun getExList() : List<Exercise> {
        return exerciseDao.getLists()
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

        fun getEmo() = listOf(Emotion(name = "emo"),
            Emotion(name = "emo"),
            Emotion(name = "emo"),
            Emotion(name = "emo"),
            Emotion(name = "emo"),
            Emotion(name = "emo"),
            Emotion(name = "emo"),
            Emotion(name = "emo"))

        fun getEventEmo() = listOf(
            EmotionEvent(
                name = "name",
                emotion = listOf(
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                )
            ),
            EmotionEvent(
                name = "name",
                emotion = listOf(
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                )
            ),EmotionEvent(
                name = "name",
                emotion = listOf(
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                )
            ),
            EmotionEvent(
                name = "name",
                emotion = listOf(
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                )
            ),EmotionEvent(
                name = "name",
                emotion = listOf(
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                )
            ),
            EmotionEvent(
                name = "name",
                emotion = listOf(
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                )
            ),EmotionEvent(
                name = "name",
                emotion = listOf(
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                )
            ),
            EmotionEvent(
                name = "name",
                emotion = listOf(
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                )
            ),EmotionEvent(
                name = "name",
                emotion = listOf(
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                )
            ),
            EmotionEvent(
                name = "name",
                emotion = listOf(
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                )
            ),EmotionEvent(
                name = "name",
                emotion = listOf(
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                )
            ),
            EmotionEvent(
                name = "name",
                emotion = listOf(
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                    Emotion(name = "emo"),
                )
            ),
        )

    }
}