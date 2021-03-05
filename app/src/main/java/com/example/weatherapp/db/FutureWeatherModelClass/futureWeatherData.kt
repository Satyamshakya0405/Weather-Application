package com.example.weatherapp.db.FutureWeatherModelClass

import com.example.weatherapp.db.CurrentWeatherModelClass.Location

data class futureWeatherData(
    val forecast: Forecast,
    val location: Location
)