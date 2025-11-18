package com.mini.taskboard.multi.feature.taskboard.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mini.taskboard.multi.core.domain.model.Task
import com.mini.taskboard.multi.core.domain.usecase.AddTaskUseCase
import com.mini.taskboard.multi.core.domain.usecase.DeleteTaskUseCase
import com.mini.taskboard.multi.core.domain.usecase.GetTaskByIdUseCase
import com.mini.taskboard.multi.core.domain.usecase.ObserveTasksUseCase
import com.mini.taskboard.multi.core.domain.usecase.SyncTasksUseCase
import com.mini.taskboard.multi.core.domain.usecase.UpdateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskBoardViewModel @Inject constructor(private val addTaskUseCase: AddTaskUseCase,
                                             private val deleteTaskUseCase: DeleteTaskUseCase,
                                             private val getITaskByIdUseCase: GetTaskByIdUseCase,
                                             observeTasksUseCase: ObserveTasksUseCase,
                                             private val syncTasksUseCase: SyncTasksUseCase,
                                             private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel(){

    val uiState: StateFlow<TaskUiState> = observeTasksUseCase.invoke().map { tasks ->
        when {
            tasks.isEmpty() -> TaskUiState.Empty
            else -> TaskUiState.Success(tasks)
        }
    }  .catch { emit(TaskUiState.Error(it.localizedMessage ?: "Unknown")) }
        .stateIn(viewModelScope, SharingStarted.Lazily, TaskUiState.Loading)


    fun addTask(title: String, description: String){
        viewModelScope.launch {
            addTaskUseCase.invoke(title, description)
        }
    }

    suspend fun loadTaskById(id: Long): Task? {
         return   getITaskByIdUseCase.invoke(id)
    }

    fun toggleComplete(task: Task){
        viewModelScope.launch {
            updateTaskUseCase.invoke(task.copy(isCompleted = !task.isCompleted,
                updatedAt = System.currentTimeMillis()))
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch {
            updateTaskUseCase.invoke(task)
        }
    }

    fun deleteTask(id: Long){
        viewModelScope.launch {
            deleteTaskUseCase.invoke(id)
        }
    }

    fun syncTasks(replaceLocal: Boolean){
        viewModelScope.launch {
            syncTasksUseCase.invoke(replaceLocal)
        }
    }

}