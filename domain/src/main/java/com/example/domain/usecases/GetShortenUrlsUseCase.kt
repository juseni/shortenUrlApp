package com.example.domain.usecases

import com.example.domain.core.Result
import com.example.domain.model.Shorten
import com.example.domain.repository.ShortenRepository
import javax.inject.Inject

class GetShortenUrlsUseCase @Inject constructor(
    private val repository: ShortenRepository
) {
    suspend operator fun invoke(url: String): List<Shorten> {
        return when (val result = repository.getShortenUrlFromApi(url)) {
            is Result.Success -> {
                repository.insertShortenUrl(result.data)
                repository.getAllShortenUrlsFromDb().orEmpty()
            }
            is Result.Error ->
                repository.getAllShortenUrlsFromDb().orEmpty()
        }
    }
}