package com.example.data

import com.example.data.db.dao.ShortenDao
import com.example.data.network.ShortenApiClient
import com.example.data.network.ShortenService
import com.example.data.network.response.ShortenResponse
import com.example.data.platform.NetworkHandler
import com.example.data.repository.ShortenRepositoryImpl
import com.example.data.utils.shorten
import com.example.data.utils.shortenEntity
import com.example.data.utils.shortenModel
import com.example.domain.core.Result
import com.example.domain.exception.ConnectionException
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ShortenRepositoryTest {

    @get:Rule
    val rule: MockitoRule = MockitoJUnit.rule()

    private var gson = Gson()

    @Mock
    lateinit var apiClient: ShortenApiClient

    @Mock
    lateinit var networkChecker: NetworkHandler

    @Mock
    lateinit var dataBase: ShortenDao

    private lateinit var repositoryImpl: ShortenRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        given(networkChecker.isConnected()).willReturn(true)
        runBlocking {
            given(apiClient.getShortenUrl("www.google.com")).willReturn(
                Response.success(
                    ShortenResponse(
                        true,
                        shortenModel
                    )
                )
            )
        }
        val apiService = ShortenService(apiClient)
        repositoryImpl = ShortenRepositoryImpl(apiService, gson, dataBase, networkChecker)
    }

    @Test
    fun getShortenUrlFromApi_Success() = runBlocking {
            val result = repositoryImpl.getShortenUrlFromApi("www.google.com")
            assertEquals(Result.Success(shorten), result)
        }

    @Test
    fun getShortenUrlFromApi_Failed() = runBlocking {
            given(apiClient.getShortenUrl("www.google.com")).willReturn(
                Response.success(
                    ShortenResponse(false, null)
                )
            )

            // then
            val result = repositoryImpl.getShortenUrlFromApi("www.google.com")
            assertEquals(Result.Error(ConnectionException()), result)
        }

    @Test
    fun getAllShortenUrls() = runBlocking {
        // given
        given(dataBase.getAllShortenUrls()).willReturn(listOf(shortenEntity))
        val result = repositoryImpl.getAllShortenUrlsFromDb()

        // Then the retrieved ShortenUrls matches the original Shorten object
        assertEquals(listOf(shorten), result)
    }

    @Test
    fun deleteShorten() = runBlocking {
        // given
        given(dataBase.deleteShortenByUrl(0)).willReturn(1)
        val result = repositoryImpl.deleteShortenById(0)

        //then
        assertEquals(true, result)
    }
}