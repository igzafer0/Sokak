package com.igzafer.sokak.presentation.di

import com.igzafer.sokak.data.repository.NewsRepositoryImpl
import com.igzafer.sokak.data.repository.dataSource.NewsRemoteDataSource
import com.igzafer.sokak.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource)
    }
}