package com.igzafer.sokak.presentation.di

import com.igzafer.sokak.presentation.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun porvideNewsAdapter(): NewsAdapter {
        return NewsAdapter()
    }
}