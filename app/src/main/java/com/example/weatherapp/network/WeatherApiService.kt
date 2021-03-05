package com.example.weatherapp.network

import com.example.weatherapp.db.CurrentWeatherModelClass.CurrentWeatherData
import com.example.weatherapp.db.FutureWeatherModelClass.futureWeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("current.json?key=3eb097b2112540a5bea132805201810")
    suspend fun getCurrentWeather(@Query("q")city:String): CurrentWeatherData

    @GET("forecast.json?key=3eb097b2112540a5bea132805201810")
    suspend fun getFutureWeather(@Query("q")city:String,@Query("days")days:Int): futureWeatherData

}