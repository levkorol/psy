package ru.harlion.psy.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.reflect.TypeToken
import ru.harlion.psy.AppApplication
import ru.harlion.psy.data.dao.EmoDiaryDao
import ru.harlion.psy.data.dao.ExerciseDao
import ru.harlion.psy.data.dao.PollDao
import ru.harlion.psy.models.Answer
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.Poll
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.models.emotions.EmotionEvent
import ru.harlion.psy.ui.main.diary_emotions.table_emotions.TableEmotionsFragment


@Database(
    entities = [Exercise::class, EmotionEvent::class, Poll::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ConverterApp::class)
abstract class DataBaseApp : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun emotionEventDao(): EmoDiaryDao
    abstract fun pollDao(): PollDao
}

object ConverterApp {
    @TypeConverter
    fun typeExToString(type: TypeEx): String = AppApplication.gson.toJson(type)

    @TypeConverter
    fun stringToTypeEx(string: String): TypeEx =
        AppApplication.gson.fromJson(string, TypeEx::class.java)


    @TypeConverter
    fun listToString(type: List<String>): String = AppApplication.gson.toJson(type)

    @TypeConverter
    fun stringToTList(string: String): List<String> =
        AppApplication.gson.fromJson(string, object : TypeToken<List<String?>?>() {}.type)


    @TypeConverter
    fun hashToEmotion(type: HashSet<Emotion>): String = type.joinToString(",") {
        it.categoryEmotions.id.toString() + ":" + it.indexCat
    }

    @TypeConverter
    fun emotionToTHash(string: String): HashSet<Emotion> {
        return if(string.isNotEmpty()) {
            string.split(",").mapTo(HashSet()) {
                val (catIndex, emoIndex) = it.split(":")
                Emotion(TableEmotionsFragment.catalogEmo[catIndex.toInt()], emoIndex.toInt())

            }
        } else {
            hashSetOf()
        }
    }


    @TypeConverter
    fun listAnswerToString(type: List<Answer>): String = AppApplication.gson.toJson(type)

    @TypeConverter
    fun stringToTListAnswer(string: String): List<Answer> =
        AppApplication.gson.fromJson(string, object : TypeToken<List<Answer?>?>() {}.type)

}