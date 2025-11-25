package com.mini.taskboard.multi.core.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val desc: String,
    val isCompleted: Boolean,
    val updatedAt: Long
)