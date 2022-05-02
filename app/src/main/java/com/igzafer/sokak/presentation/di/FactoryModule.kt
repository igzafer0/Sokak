package com.igzafer.sokak.presentation.di

import android.app.Application
import com.igzafer.sokak.domain.usecase.GetNewsHeadlinesUseCase
import com.igzafer.sokak.presentation.viewmodel.NewsViewModel
import com.igzafer.sokak.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(application, getNewsHeadlinesUseCase)
    }
}