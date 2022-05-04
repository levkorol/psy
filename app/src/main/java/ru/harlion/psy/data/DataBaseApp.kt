package ru.harlion.psy.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.reflect.TypeToken
import ru.harlion.psy.AppApplication
import ru.harlion.psy.data.dao.EmoDiaryDao
import ru.harlion.psy.data.dao.ExerciseDao
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.models.emotions.Emotion
import ru.harlion.psy.models.emotions.EmotionEvent


@Database(entities = [Exercise::class, EmotionEvent::class], version = 1, exportSchema = false)
@TypeConverters(ConverterApp::class)
abstract class DataBaseApp: RoomDatabase() {

    abstract fun exerciseDao() : ExerciseDao
    abstract fun emotionEventDao() : EmoDiaryDao
}

object ConverterApp {
    @TypeConverter
    fun typeExToString(type : TypeEx): String = AppApplication.gson.toJson(type)

    @TypeConverter
    fun stringToTypeEx(string: String): TypeEx =
        AppApplication.gson.fromJson(string, TypeEx::class.java)

    @TypeConverter
    fun listToString(type : List<String>): String = AppApplication.gson.toJson(type)

    @TypeConverter
    fun stringToTList(string: String): List<String> =
        AppApplication.gson.fromJson(string, object : TypeToken<List<String?>?>() {}.type)

    @TypeConverter
    fun listToEmotion(type : List<Emotion>): String = AppApplication.gson.toJson(type)

    @TypeConverter
    fun emotionToTList(string: String): List<Emotion> =
        AppApplication.gson.fromJson(string, object : TypeToken<List<Emotion?>?>() {}.type)
}