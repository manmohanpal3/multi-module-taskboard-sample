package com.mini.taskboard.multi.feature.taskboard.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mini.taskboard.multi.feature.taskboard.vm.TaskBoardViewModel
import com.mini.taskboard.multi.feature.taskboard.vm.TaskUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    onNavigateToAdd:()->Unit,
    onNavigateToEdit:(Long)->Unit,
    vm: TaskBoardViewModel = hiltViewModel()
){
    val state by vm.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Task Board")
            },
                actions = {
                    IconButton(onClick = {
                        vm.syncTasks(false)
                    }) {
                        Icon(Icons.Default.Sync, contentDescription = "Sync")
                    }
                    IconButton(onClick = {
                        vm.syncTasks(true)
                    }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Replace local with remote")
                    }
                })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onNavigateToAdd()
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()){
            when(state){
                TaskUiState.Empty -> Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(text = "No tasks available")
                    Button(onClick = onNavigateToAdd) {
                        Text(text = "Add Task")
                    }
                }
                is TaskUiState.Error -> Text(text = (state as TaskUiState.Error).message, modifier = Modifier.align(Alignment.Center))
                TaskUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is TaskUiState.Success -> {
                    val task = (state as  TaskUiState.Success).tasks
                    LazyColumn {
                        items(task) { task ->
                            TaskItem(
                                task = task,
                                onToggle = { vm.toggleComplete(it) },
                                onEdit = { onNavigateToEdit(it.id) },
                                onDelete = { vm.deleteTask(it.id) }
                            )
                    }
                }
            }
        }
    }
}}