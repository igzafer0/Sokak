package com.igzafer.sokak.presentation.di

import com.igzafer.sokak.BuildConfig
import com.igzafer.sokak.data.api.NewsAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    val interceptor=HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
    @Singleton
    @Provides
    fun provideNewsAPIService(retrofit: Retrofit):NewsAPIService{
        return retrofit.create(NewsAPIService::class.java)
    }
}