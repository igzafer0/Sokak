package com.igzafer.sokak.data.repository.dataSource

import com.igzafer.sokak.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse>
}