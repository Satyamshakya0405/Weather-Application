package com.example.weatherapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.weatherapp.Repository.WeatherRepository
import com.example.weatherapp.db.CurrentWeatherModelClass.CurrentWeatherData
import com.example.weatherapp.db.ForecastDatabase
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(
    application: Application
) : AndroidViewModel(application) {

        private val weatherRepository:WeatherRepository
        val mutableLivedata:MutableLiveData<CurrentWeatherData> =  MutableLiveData()
//        val currentlivedata:LiveData<CurrentWeatherData>get() = mutableLivedata
        init {
            val currentWeatherDao = ForecastDatabase.getForecastDatabase(application).currentWeatherDao()
           val futureWeatherDao=ForecastDatabase.getForecastDatabase(application).futureWeatherDao()
            weatherRepository = WeatherRepository(currentWeatherDao,futureWeatherDao)

        }

    fun getdata(){
        viewModelScope.launch {
            val response=weatherRepository.getCurrentWeather(true)
            mutableLivedata.value=response
        }
    }
    }


