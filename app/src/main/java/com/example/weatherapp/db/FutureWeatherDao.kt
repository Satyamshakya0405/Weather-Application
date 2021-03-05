package com.example.weatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.db.FutureWeatherModelClass.Forecastday
@Dao
interface FutureWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecastday: List<Forecastday>)

    @Query("SELECT * FROM future_weather where date(date) >=  date(:startDate) ")
    fun getFutureWeather(startDate:String):LiveData<List<Forecastday>>

    @Query("SELECT count(id) FROM future_weather  where date(date) >=  date(:startDate)   ")
    fun countFutureWeather(startDate:String):Int

    @Query("DELETE FROM future_weather where date(date)<date(:startDate)")
    fun deleteOldEntries(startDate: String)
}