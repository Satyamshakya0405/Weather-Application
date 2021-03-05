package com.example.weatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.db.CurrentWeatherModelClass.CURRENT_WEATHER_ID
import com.example.weatherapp.db.CurrentWeatherModelClass.CurrentWeatherData
import com.example.weatherapp.db.unit_localised.ImperialCurrentWeatherEntry
import com.example.weatherapp.db.unit_localised.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(currentWeatherData: CurrentWeatherData)

    @Query("SELECT * FROM Current_Weather WHERE id=$CURRENT_WEATHER_ID")
      suspend fun getWeatherMetric():CurrentWeatherData

//    @Query("SELECT * FROM Current_Weather WHERE id=$CURRENT_WEATHER_ID")
//    suspend fun getWeatherImperial():MetricCurrentWeatherEntry


}