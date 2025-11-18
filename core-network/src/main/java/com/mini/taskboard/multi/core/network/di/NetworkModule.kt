package com.mini.taskboard.multi.core.network.di


import com.mini.taskboard.multi.core.network.DummyNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideDummyNetwork(): DummyNetwork {
        return DummyNetwork()
    }
}