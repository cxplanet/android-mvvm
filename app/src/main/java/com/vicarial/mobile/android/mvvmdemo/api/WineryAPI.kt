package com.vicarial.mobile.android.mvvmdemo.api

import com.vicarial.mobile.android.mvvmdemo.data.Winery
import retrofit2.http.GET

interface WineryAPI {
    companion object {
        const val BASE_URL = "https://dev.vicarial.com/api/"
    }

    @GET("wineries")
    suspend fun getWineries(): List<Winery>
}