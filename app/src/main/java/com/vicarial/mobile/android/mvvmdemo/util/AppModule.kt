package com.vicarial.mobile.android.mvvmdemo.util

import android.app.Application
import androidx.room.Room
import com.vicarial.mobile.android.mvvmdemo.api.WineryAPI
import com.vicarial.mobile.android.mvvmdemo.data.WineTourDB
import com.vicarial.mobile.android.mvvmdemo.util.DDConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// see https://developer.android.com/training/dependency-injection/hilt-android

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(WineryAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideWineryApi(retrofit: Retrofit): WineryAPI =
        retrofit.create(WineryAPI::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): WineTourDB =
        Room.databaseBuilder(app, WineTourDB::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

}