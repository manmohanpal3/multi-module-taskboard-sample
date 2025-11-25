package com.mini.taskboard.multi.core.domain.repository


import com.mini.taskboard.multi.core.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun observeTasks(): Flow<List<Task>>

    suspend fun addTask(task: Task)
    suspend  fun deleteTask(id: Long)
    suspend fun getTaskById(id: Long): Task?
    suspend fun updateTask(task: Task)
    suspend fun syncFromRemote(replaceLocal: Boolean = false)
}