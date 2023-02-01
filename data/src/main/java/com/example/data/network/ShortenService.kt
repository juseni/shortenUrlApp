package com.example.data.network

import com.example.data.model.ShortenModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShortenService @Inject constructor(
    private val apiClient: ShortenApiClient
) {

    suspend fun getShorten(url: String): ShortenModel? {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getShortenUrl(url)
            response.body()?.result
        }
    }
}