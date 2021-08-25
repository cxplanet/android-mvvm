package com.vicarial.mobile.android.mvvmdemo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vicarial.mobile.android.mvvmdemo.util.DDConstants.TABLE_WINERY_NAME

@Entity(tableName = TABLE_WINERY_NAME)
data class Winery(
    @PrimaryKey val id: Int,
    val name: String,
    val address: String,
    val cityStateZip: String,
    val plusCode: String,
    val siteUrl: String,
    val regionId: Int = 0,
)