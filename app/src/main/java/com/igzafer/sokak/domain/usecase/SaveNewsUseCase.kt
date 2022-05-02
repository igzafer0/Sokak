package com.igzafer.sokak.domain.usecase

import com.igzafer.sokak.data.model.Article
import com.igzafer.sokak.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article)=newsRepository.saveNews(article)
}