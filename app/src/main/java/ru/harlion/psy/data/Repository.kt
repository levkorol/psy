package ru.harlion.psy.data

import android.content.Context
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.models.emotions.EmotionEvent

class Repository private constructor(context: Context) {

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