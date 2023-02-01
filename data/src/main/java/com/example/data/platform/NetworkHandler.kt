package com.example.data.platform

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {
    fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}