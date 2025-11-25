package com.mini.taskboard.multi.core.domain.usecase


import com.mini.taskboard.multi.core.domain.model.Task
import com.mini.taskboard.multi.core.domain.repository.TaskRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task: Task){
        taskRepository.updateTask(task)
    }
}