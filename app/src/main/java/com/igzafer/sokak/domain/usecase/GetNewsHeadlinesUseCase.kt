package com.igzafer.sokak.domain.usecase

import com.igzafer.sokak.data.model.APIResponse
import com.igzafer.sokak.data.util.Resource
import com.igzafer.sokak.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country: String, page: Int): Resource<APIResponse> {
        return newsRepository.getNewsHeadlines(country, page)
    }
}