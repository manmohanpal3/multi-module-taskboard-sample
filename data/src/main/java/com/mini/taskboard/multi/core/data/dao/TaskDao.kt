package com.mini.taskboard.multi.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mini.taskboard.multi.core.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
   @Query("Select * from tasks order by id desc")
   fun getAllTasks(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<TaskEntity>)

    @Query("Select * from tasks where id = :id")
    suspend fun getTaskById(id: Long): TaskEntity?
    @Update
    suspend fun update(entity: TaskEntity)

    @Query("Delete from tasks where id = :id")
    suspend fun deleteById(id:Long)

    @Query("Delete from tasks")
    suspend fun clearAll()

}