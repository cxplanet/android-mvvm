package com.vicarial.mobile.android.mvvmdemo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WineryDAO {
    // ObservableReads as a kotlin flow.
    // https://medium.com/androiddevelopers/room-flow-273acffe5b57
    @Query("SELECT * FROM wineries")
    fun getAllWineries(): Flow<List<Winery>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWineries(wineries: List<Winery>)

    @Query("DELETE FROM wineries")
    suspend fun deleteAllWineries()
}