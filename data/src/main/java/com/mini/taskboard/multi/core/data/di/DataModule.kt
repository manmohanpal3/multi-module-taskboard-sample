package com.mini.taskboard.multi.core.data.di

import android.content.Context
import androidx.room.Room
import com.mini.taskboard.multi.core.common.Constants
import com.mini.taskboard.multi.core.data.dao.TaskDao
import com.mini.taskboard.multi.core.data.database.AppDatabase
import com.mini.taskboard.multi.core.data.repository.TaskRepositoryImpl
import com.mini.taskboard.multi.core.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindings {
    @Binds
    @Singleton
    abstract fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository
}


@Module
@InstallIn(SingletonComponent::class)
object  DataModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder<AppDatabase>(
            context = context,
            name = Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(database: AppDatabase): TaskDao {
        return database.taskDao()
    }
}