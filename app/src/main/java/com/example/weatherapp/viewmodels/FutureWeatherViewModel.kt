package com.example.weatherapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Repository.WeatherRepository
import com.example.weatherapp.db.CurrentWeatherModelClass.CurrentWeatherData
import com.example.weatherapp.db.ForecastDatabase
import com.example.weatherapp.db.FutureWeatherModelClass.Forecastday
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FutureWeatherViewModel(application:Application) : AndroidViewModel(application) {

    private val weatherRepository: WeatherRepository
    val mutableLivedata: MutableLiveData<List<Forecastday>> =  MutableLiveData()
    init {
        val futureWeatherDao=ForecastDatabase.getForecastDatabase(application).futureWeatherDao()
        val currentWeatherDao=ForecastDatabase.getForecastDatabase(application).currentWeatherDao()
        weatherRepository= WeatherRepository(currentWeatherDao,futureWeatherDao)
    }
     fun getData()
    {
        viewModelScope.launch {
            val response=weatherRepository.getFutureWeather(true)
            mutableLivedata.value=response.value
        }

    }

}