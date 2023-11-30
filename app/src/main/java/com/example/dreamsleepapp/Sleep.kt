package com.example.dreamsleepapp

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sleep(
    @Json(name="date")var date : String,
    @Json(name = "dreams")var dream : String,
    @Json(name = "hours_slept") var hrs : String,
    @Json(name = "id") var id : String,
    @Json(name="sleep_quality")var rating : String,

)
