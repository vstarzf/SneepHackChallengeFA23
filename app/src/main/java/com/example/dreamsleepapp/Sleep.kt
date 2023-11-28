package com.example.dreamsleepapp

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sleep(
    @Json(name = "id") var id : Int,
    @Json(name = "hours_slept") var hrs : Int,
    @Json(name = "dreams")var dream : String,
    @Json(name="sleep_quality")var rating : Int,
    @Json(name="date")var date : String
)
