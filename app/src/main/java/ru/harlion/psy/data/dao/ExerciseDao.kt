package ru.harlion.psy.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.harlion.psy.models.Exercise
import ru.harlion.psy.models.TypeEx

@Dao
abstract class ExerciseDao {

    @Insert
    abstract fun add(exercise: Exercise)

    @Update
    abstract fun update(exercise: Exercise)

    @Query("DELETE FROM exercise WHERE id = :id")
    abstract fun deleteById(id : Long)

    @Query("SELECT * FROM exercise where type = :typeEx")
    abstract fun getLists(typeEx : TypeEx): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercise WHERE id = (:id)")
    abstract fun exById(id: Long): LiveData<Exercise>

    @Query("SELECT * from exercise where isArchive = :isArchive and type = :typeEx")
    abstract fun getListsByArchive(typeEx : TypeEx, isArchive : Boolean ): LiveData<List<Exercise>>


    @Query("UPDATE exercise SET  isArchive = coalesce(:isArchive, isArchive) WHERE id = :id")
    abstract fun updateFields(id: Long, isArchive: Boolean)
}