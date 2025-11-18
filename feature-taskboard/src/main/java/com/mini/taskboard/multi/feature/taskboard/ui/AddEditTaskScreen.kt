package com.mini.taskboard.multi.feature.taskboard.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mini.taskboard.multi.core.domain.model.Task
import com.mini.taskboard.multi.feature.taskboard.vm.TaskBoardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreen(
    taskId: Long = 0L,
    onDone: () -> Unit,
    onBack: () -> Unit,
    vm: TaskBoardViewModel = hiltViewModel()
){
    val title = remember { mutableStateOf("") }
    val desc = remember { mutableStateOf("") }
    val isLoaded = remember { mutableStateOf(false) }

    LaunchedEffect(taskId) {
        if (taskId > 0) {
            val existingTask = vm.loadTaskById(taskId)
            existingTask?.let {
                title.value = it.title
                desc.value = it.desc
            }
        }
            isLoaded.value = true
    }

    if(!isLoaded.value){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text =  if (taskId > 0)  "Edit Task" else "Add Task") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                })
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally) {

            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = title.value, onValueChange = { title.value = it }, label = { Text("Title") })
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField( modifier = Modifier.fillMaxWidth(),
                value = desc.value, onValueChange = { desc.value = it }, label = { Text("Description") })
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if(title.value.isNotEmpty()){
                    if(taskId > 0){
                        val task = Task(id = taskId, title = title.value, desc = desc.value)
                        vm.updateTask(task)
                    }else{
                        vm.addTask(title.value, desc.value)
                    }
                    onDone()
                }
            }) {
                Text(text =  if (taskId > 0)  "Update" else "Save")
            }
        }
    }
}