package com.mini.taskboard.multi.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mini.taskboard.multi.core.data.dao.TaskDao
import com.mini.taskboard.multi.core.data.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase()  {
    abstract fun taskDao(): TaskDao
}