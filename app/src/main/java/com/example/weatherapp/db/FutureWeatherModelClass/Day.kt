package com.example.weatherapp.db.FutureWeatherModelClass

import androidx.room.Embedded
import com.example.weatherapp.db.CurrentWeatherModelClass.Condition

data class Day(
    val avgtemp_c: Double,
    val avgtemp_f: Double,
    val avgvis_km: Double,
    val avgvis_miles: Double,
    @Embedded(prefix = "condition_")
    val condition: Condition,
    val maxtemp_c: Double,
    val maxtemp_f: Double,
    val maxwind_kph: Double,
    val maxwind_mph: Double,
    val mintemp_c: Double,
    val mintemp_f: Double,
    val totalprecip_in: Double,
    val totalprecip_mm: Double,
    val uv: Double
)