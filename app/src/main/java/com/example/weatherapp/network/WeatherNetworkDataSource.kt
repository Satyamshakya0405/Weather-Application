package com.example.weatherapp.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.db.CurrentWeatherModelClass.CurrentWeatherData
import com.example.weatherapp.db.FutureWeatherModelClass.futureWeatherData
import com.example.weatherapp.db.unit_localised.MetricCurrentWeatherEntry

class WeatherNetworkDataSource(private val weatherApiService: WeatherApiService) {
    private val _downloadedCurrentWeather=MutableLiveData<CurrentWeatherData>()
     val downloadedCurrentWeather:LiveData<CurrentWeatherData> get() = _downloadedCurrentWeather

    private val _downloadedFutureWeather=MutableLiveData<futureWeatherData>()
    val downloadedFutureWeather:LiveData<futureWeatherData> get() = _downloadedFutureWeather

    suspend fun fetchCurrentWeather(location:String,languageCode:String)
    {
        try {
            val fetchedCurrentWeatherData=weatherApiService.getCurrentWeather(location)
            _downloadedCurrentWeather.postValue(fetchedCurrentWeatherData)
        }catch (e:Exception)
        {
            Log.e("Connectivity","NO CONNECTION",e)
        }

    }

    suspend fun fetchFutureWeather(location:String,languageCode:String)
    {
        try {
            val fetchedFutureWeatherData=weatherApiService.getFutureWeather(location,10)
            _downloadedFutureWeather.postValue(fetchedFutureWeatherData)

            if(fetchedFutureWeatherData==null)
            {
                Log.e("Connectivity","NULL FUTURE")
            }
        }catch (e:Exception)
        {
            Log.e("Connectivity","NO CONNECTION",e)
        }

    }
}