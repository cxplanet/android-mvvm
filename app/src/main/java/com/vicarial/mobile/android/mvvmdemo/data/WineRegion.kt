package com.vicarial.mobile.android.mvvmdemo.data

data class Region(
    val id: Int,
    val name: String,
    val geodata: Int, // short term - resource id for geojson describinig region
    val location: String // plusCode for centroid of polygon
)