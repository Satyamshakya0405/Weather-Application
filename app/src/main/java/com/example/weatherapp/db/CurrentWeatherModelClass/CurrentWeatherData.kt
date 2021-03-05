package com.example.weatherapp.db.CurrentWeatherModelClass

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_WEATHER_ID=0
@Entity(tableName = "Current_Weather")
data class CurrentWeatherData(
    @Embedded(prefix = "current_")
    val current: Current,
    @Embedded(prefix = "location_")
    val location: Location
)
{
    @PrimaryKey(autoGenerate = false)
    var id:Int= CURRENT_WEATHER_ID
}