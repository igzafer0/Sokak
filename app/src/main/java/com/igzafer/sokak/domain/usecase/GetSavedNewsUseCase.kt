package com.igzafer.sokak.domain.usecase

import com.igzafer.sokak.data.model.Article
import com.igzafer.sokak.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    fun execute():Flow<List<Article>>{
        return newsRepository.getSavedNews()
    }
}