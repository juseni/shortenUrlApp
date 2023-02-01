package com.example.data.repository

import com.example.data.db.dao.ShortenDao
import com.example.data.network.ShortenService
import com.example.data.platform.NetworkHandler
import com.example.data.utils.isDeletedSuccessfully
import com.example.data.utils.mapToList
import com.example.data.utils.mapToObject
import com.example.domain.core.Result
import com.example.domain.exception.ConnectionException
import com.example.domain.model.Shorten
import com.example.domain.repository.ShortenRepository
import com.google.gson.Gson
import javax.inject.Inject

class ShortenRepositoryImpl @Inject constructor(
    private val api: ShortenService,
    private val gson: Gson,
    private val dao: ShortenDao,
    private val networkChecker: NetworkHandler
): ShortenRepository {

    override suspend fun getShortenUrlFromApi(url: String): Result<Shorten> {
        return if (networkChecker.isConnected()) {
            val response = api.getShorten(url)
            if (response != null) {
                Result.Success(gson.mapToObject(response))
            } else {
                Result.Error(ConnectionException())
            }
        } else {
            Result.Error(ConnectionException())
        }
    }

    override suspend fun getAllShortenUrlsFromDb(): List<Shorten>? =
        gson.mapToList(dao.getAllShortenUrls())

    override suspend fun insertShortenUrl(shorten: Shorten) {
        dao.insertShortenUrl(gson.mapToObject(shorten))
    }

    override suspend fun deleteShortenById(shortenId: Int): Boolean =
        dao.deleteShortenByUrl(shortenId).isDeletedSuccessfully()


}