package com.mini.taskboard.multi.core.network.model

data class TaskDto(
    val id: Long,
    val title: String,
    val desc: String,
    val isCompleted: Boolean,
    val updatedAt: Long
)
