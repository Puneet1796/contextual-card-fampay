package com.puneet.contextualcards.di

import com.puneet.contextualcards.network.Repository
import com.puneet.contextualcards.network.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsRepositoryInstance(repository: RepositoryImpl): Repository
}
