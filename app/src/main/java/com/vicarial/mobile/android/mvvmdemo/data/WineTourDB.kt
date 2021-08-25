package com.vicarial.mobile.android.mvvmdemo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Winery::class], version = 1, exportSchema = false)
abstract class WineTourDB: RoomDatabase() {

    abstract fun wineryDAO(): WineryDAO

}
