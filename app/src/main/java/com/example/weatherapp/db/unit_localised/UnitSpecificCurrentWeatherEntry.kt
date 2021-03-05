package com.example.weatherapp.db.unit_localised

interface UnitSpecificCurrentWeatherEntry {

    val temperature:Double
    val conditionText:String
    val conditionIconUrl:String
    val windSpeed:Double
    val windDirection: String
    val feelsLikeTemperature:Double
    val visibilityDistance:Double
    val precipitationVolume:Double

}