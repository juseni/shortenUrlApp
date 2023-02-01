package com.example.shortlyapphijfqp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Shorten
import com.example.domain.usecases.DeleteShortenUseCase
import com.example.domain.usecases.GetShortenUrlsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShortenViewModel @Inject constructor(
    private val getShortenUrlsUseCase: GetShortenUrlsUseCase,
    private val deleteShortenUseCase: DeleteShortenUseCase
) : ViewModel() {

    val shortenUrls = MutableLiveData<List<Shorten>>()
    val isLoading = MutableLiveData<Boolean>()
    val noDataLoaded = MutableLiveData<Boolean>()

    fun getShortenUrls(url: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            handleShortenUrls(getShortenUrlsUseCase(url))
        }
    }

    fun deleteShortenUrl(shortenId: Int) {
        viewModelScope.launch {
            handleShortenUrls(deleteShortenUseCase(shortenId))
        }
    }

    private fun handleShortenUrls(result: List<Shorten>) {
        isLoading.postValue(false)
        if (result.isNotEmpty()) {
            shortenUrls.postValue(result)
        } else {
            noDataLoaded.postValue(true)
        }
    }
}