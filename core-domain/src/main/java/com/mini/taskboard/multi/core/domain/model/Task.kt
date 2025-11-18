package com.mini.taskboard.multi.core.domain.model

data class Task(val  id: Long,val  title: String, val desc: String = "",
                val isCompleted: Boolean = false,
                val updatedAt: Long = System.currentTimeMillis())