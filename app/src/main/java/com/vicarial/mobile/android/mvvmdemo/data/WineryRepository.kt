package com.vicarial.mobile.android.mvvmdemo.data

import androidx.room.withTransaction
import com.vicarial.mobile.android.mvvmdemo.api.WineryAPI
import com.vicarial.mobile.android.mvvmdemo.util.networkBoundResource
import javax.inject.Inject

/**
 * The repository uses the WinetourDB as the source of truth,
 * but is pretty simplistic in its caching strategy.
 */

class WineryRepository @Inject constructor(
    private val api: WineryAPI,
    private val db: WineTourDB
){
    private val dao = db.wineryDAO()

    fun getWineries() = networkBoundResource(
        query = { dao.getAllWineries() },
        fetch = {
            api.getWineries()
        },
        saveFetchResult = { wineries ->
            db.withTransaction {
                dao.deleteAllWineries()
                dao.insertWineries(wineries)
            }

        }
    )
}