package com.mini.taskboard.multi.core.domain.usecase

import com.mini.taskboard.multi.core.domain.repository.TaskRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator  fun invoke(id: Long){
        taskRepository.deleteTask(id)
    }

}