package com.mini.taskboard.multi.core.domain.usecase


import com.mini.taskboard.multi.core.domain.model.Task
import com.mini.taskboard.multi.core.domain.repository.TaskRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(title: String, description: String){
        val task = Task(0L, title = title, desc = description)
        taskRepository.addTask(task)
    }
}