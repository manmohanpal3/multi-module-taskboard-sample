package com.mini.taskboard.multi.feature.taskboard.vm

import com.mini.taskboard.multi.core.domain.model.Task

sealed class TaskUiState {
    object Loading : TaskUiState()
    object Empty : TaskUiState()
    data class Success(val tasks: List<Task>) : TaskUiState()
    data class Error(val message: String) : TaskUiState()
}