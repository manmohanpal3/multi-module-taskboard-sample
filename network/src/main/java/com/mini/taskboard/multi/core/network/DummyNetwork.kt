package com.mini.taskboard.multi.core.network


import com.mini.taskboard.multi.core.network.model.TaskDto
import kotlinx.coroutines.delay

class DummyNetwork {
    suspend fun fetchTasks(): List<TaskDto> {
        delay(2000)
      return  listOf<TaskDto>(
            TaskDto(1, "Task 1", "Task ", isCompleted = false, updatedAt = System.currentTimeMillis()),
            TaskDto(2, "Task 2", desc = "Task ", isCompleted = true, updatedAt = System.currentTimeMillis()),
            TaskDto(3, "Task 3", desc = "Task ", isCompleted = false, updatedAt = System.currentTimeMillis()),
            TaskDto(4, "Task 4", desc = "Task ", isCompleted = true, updatedAt = System.currentTimeMillis()),
        )
    }
}