package com.example.weatherapp.db.unit_localised

import androidx.room.ColumnInfo

data class MetricCurrentWeatherEntry(
    @ColumnInfo(name = "temp_c")
    override val temperature: Double,
    @ColumnInfo(name = "condition_text")
    override val conditionText: String,
    @ColumnInfo(name = "condition_icon")
    override val conditionIconUrl: String,
    @ColumnInfo(name = "wind_kph")
    override val windSpeed: Double,
    @ColumnInfo(name = "wind_dir")
    override val windDirection: String,
    @ColumnInfo(name = "feelslike_c")
    override val feelsLikeTemperature: Double,
    @ColumnInfo(name = "vis_km")
    override val visibilityDistance: Double,
    @ColumnInfo(name = "precip_in")
    override val precipitationVolume: Double
) :UnitSpecificCurrentWeatherEntry