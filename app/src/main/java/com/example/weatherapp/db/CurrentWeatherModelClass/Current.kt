package com.example.weatherapp.db.CurrentWeatherModelClass

import androidx.room.Embedded

data class Current(
    val cloud: Int,
    @Embedded(prefix = "condition_")
    val condition: Condition,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val precip_in: Double,
    val precip_mm: Double,
    val temp_c: Double,
    val temp_f: Double,
    val vis_km: Double,
    val vis_miles: Double,
    val wind_dir: String,
    val wind_kph: Double,
    val wind_mph: Double
)