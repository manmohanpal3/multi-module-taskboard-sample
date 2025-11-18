package com.mini.taskboard.multi.feature.taskboard.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mini.taskboard.multi.core.domain.model.Task

@Composable
fun TaskItem(task: Task,
             onToggle:(task: Task)-> Unit,
             onEdit:(task: Task)-> Unit,
             onDelete:(task: Task)-> Unit){

    Card(modifier = Modifier
        .padding(horizontal = 8.dp, vertical = 6.dp)
        .fillMaxWidth()) {
        Row(modifier = Modifier.padding(12.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = task.title, style = MaterialTheme.typography.titleMedium)
                if (task.desc.isNotEmpty()) {
                    Text(text = task.desc, style = MaterialTheme.typography.bodySmall)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(checked = task.isCompleted, onCheckedChange = { onToggle(task) })
            IconButton(onClick = { onEdit(task) }) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = { onDelete(task) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}