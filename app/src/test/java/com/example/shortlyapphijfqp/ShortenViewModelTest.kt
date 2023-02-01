package com.example.shortlyapphijfqp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.usecases.DeleteShortenUseCase
import com.example.domain.usecases.GetShortenUrlsUseCase
import com.example.shortlyapphijfqp.utils.shorten
import com.example.shortlyapphijfqp.viewmodel.ShortenViewModel
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
class ShortenViewModelTest {

    @get:Rule
    val rule: MockitoRule = MockitoJUnit.rule()
    @get:Rule
    var rule2: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getShortenUrlsUseCase: GetShortenUrlsUseCase

    @Mock
    lateinit var deleteShortenUseCase: DeleteShortenUseCase

    private lateinit var viewModel: ShortenViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = ShortenViewModel(getShortenUrlsUseCase, deleteShortenUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun getShortenUrlsTest()  {
        viewModel.shortenUrls.observeForever {
            assertEquals(listOf(shorten), it)
        }
        runBlocking {
            launch(Dispatchers.Main) {
                given(getShortenUrlsUseCase.invoke("www.google.com")).willReturn(listOf(shorten))
                viewModel.getShortenUrls("www.google.com")
            }
        }
    }

    @Test
    fun deleteShortenTest() {
        viewModel.shortenUrls.observeForever {
            assertTrue(it.isEmpty())
        }
        runBlocking {
            launch {
                given(deleteShortenUseCase.invoke(0)).willReturn(emptyList())
                viewModel.deleteShortenUrl(0)
            }
        }
    }
}