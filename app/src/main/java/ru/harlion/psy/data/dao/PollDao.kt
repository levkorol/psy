package ru.harlion.psy.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.harlion.psy.models.Poll

@Dao
abstract class PollDao {

    @Insert
    abstract fun add(poll: Poll)

    @Update
    abstract fun update(poll: Poll)

    @Query("DELETE FROM poll WHERE id = :id")
    abstract fun deleteById(id : Long)

    @Query("SELECT * FROM poll order by dateCreate desc ")
    abstract fun getLists(): LiveData<List<Poll>>

    @Query("SELECT * FROM poll WHERE id = (:id)")
    abstract fun pollById(id: Long): LiveData<Poll>
}