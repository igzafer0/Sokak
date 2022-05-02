package com.igzafer.sokak.data.repository.dataSourceImpl

import com.igzafer.sokak.data.api.NewsAPIService
import com.igzafer.sokak.data.model.APIResponse
import com.igzafer.sokak.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response


class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService,
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(
        country: String,
        page: Int
    ): Response<APIResponse> {
        return newsAPIService.getTopHeadlines(country, page)
    }
}