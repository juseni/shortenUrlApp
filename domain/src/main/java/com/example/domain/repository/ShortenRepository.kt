package com.example.domain.repository

import com.example.domain.core.Result
import com.example.domain.model.Shorten

interface ShortenRepository {

    suspend fun getShortenUrlFromApi(url: String): Result<Shorten>

    suspend fun getAllShortenUrlsFromDb(): List<Shorten>?

    suspend fun insertShortenUrl(shorten: Shorten)

    suspend fun deleteShortenById(shortenId: Int): Boolean
}