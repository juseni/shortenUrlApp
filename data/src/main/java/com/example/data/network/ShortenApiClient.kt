package com.example.data.network

import com.example.data.model.ShortenModel
import com.example.data.network.response.ShortenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ShortenApiClient {

    companion object {
        private const val SHORTEN = "shorten"
        private const val URL_PARAM = "url"
    }

    @POST(SHORTEN)
    suspend fun getShortenUrl(@Query(URL_PARAM) url: String) : Response<ShortenResponse>
}