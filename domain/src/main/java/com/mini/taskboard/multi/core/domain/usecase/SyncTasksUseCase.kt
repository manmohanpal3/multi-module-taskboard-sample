package com.mini.taskboard.multi.core.domain.usecase


import com.mini.taskboard.multi.core.domain.repository.TaskRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend  operator fun invoke(replaceLocal: Boolean){
        taskRepository.syncFromRemote(replaceLocal)
    }
}