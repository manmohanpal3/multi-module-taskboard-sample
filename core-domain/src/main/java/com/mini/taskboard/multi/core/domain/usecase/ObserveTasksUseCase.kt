package com.mini.taskboard.multi.core.domain.usecase


import com.mini.taskboard.multi.core.domain.model.Task
import com.mini.taskboard.multi.core.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke(): Flow<List<Task>>{
      return  taskRepository.observeTasks()
    }
}