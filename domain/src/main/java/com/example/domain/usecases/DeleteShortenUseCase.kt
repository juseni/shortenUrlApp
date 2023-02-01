package com.example.domain.usecases

import com.example.domain.model.Shorten
import com.example.domain.repository.ShortenRepository
import javax.inject.Inject

class DeleteShortenUseCase @Inject constructor(
    private val repository: ShortenRepository
) {
    suspend operator fun invoke(shortenId: Int): List<Shorten> {
        repository.deleteShortenById(shortenId)
        return repository.getAllShortenUrlsFromDb().orEmpty()
    }
}