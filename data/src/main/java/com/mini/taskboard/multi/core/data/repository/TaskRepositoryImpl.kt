package com.mini.taskboard.multi.core.data.repository


import com.mini.taskboard.multi.core.data.dao.TaskDao
import com.mini.taskboard.multi.core.data.entity.TaskEntity
import com.mini.taskboard.multi.core.domain.model.Task
import com.mini.taskboard.multi.core.domain.repository.TaskRepository
import com.mini.taskboard.multi.core.network.DummyNetwork
import com.mini.taskboard.multi.core.network.model.TaskDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao,
                                             private val dummyNetwork: DummyNetwork
) :
    TaskRepository {
    override fun observeTasks(): Flow<List<Task>> {
     return   taskDao.getAllTasks().map {  list ->
           list.map {
               it.toDomain()
           }
       }
    }

    override suspend fun addTask(task: Task) {
      val id = if (task.id <= 0){
          System.currentTimeMillis()
      }else{
          task.id
      }
        taskDao.insert(task.toEntity().copy(id))
    }

    override suspend fun deleteTask(id: Long) {
       taskDao.deleteById(id)
    }

    override suspend fun getTaskById(id: Long): Task? {
        return taskDao.getTaskById(id)?.toDomain()
    }

    override suspend fun updateTask(task: Task) {
       taskDao.update(task.toEntity())
    }


    override suspend fun syncFromRemote(replaceLocal: Boolean) {
        val remoteTasks = dummyNetwork.fetchTasks()
        val remoteTaskEntity = remoteTasks.map {  list ->
            list.toEntity()
        }
        if (replaceLocal){
            taskDao.clearAll()
        }
        taskDao.insertAll(remoteTaskEntity)
    }
}

fun TaskEntity.toDomain() = Task(id, title, desc, isCompleted = isCompleted, updatedAt = updatedAt)

fun TaskDto.toEntity() = TaskEntity(id, title, desc, isCompleted = isCompleted, updatedAt = updatedAt)

fun Task.toEntity() = TaskEntity(id, title, desc, isCompleted = isCompleted, updatedAt = updatedAt)

