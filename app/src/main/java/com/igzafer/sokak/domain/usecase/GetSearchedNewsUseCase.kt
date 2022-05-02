package com.igzafer.sokak.domain.usecase

import com.igzafer.sokak.data.model.APIResponse
import com.igzafer.sokak.data.util.Resource
import com.igzafer.sokak.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(searcQuery: String): Resource<APIResponse> {
        return newsRepository.getSearchedNews(searcQuery)
    }
}