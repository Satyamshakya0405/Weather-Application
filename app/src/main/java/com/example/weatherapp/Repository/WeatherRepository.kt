package com.example.weatherapp.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weatherapp.db.CurrentWeatherDao
import com.example.weatherapp.db.CurrentWeatherModelClass.CurrentWeatherData
import com.example.weatherapp.db.FutureWeatherDao
import com.example.weatherapp.db.FutureWeatherModelClass.Forecastday
import com.example.weatherapp.db.FutureWeatherModelClass.futureWeatherData
import com.example.weatherapp.db.unit_localised.MetricCurrentWeatherEntry
import com.example.weatherapp.network.WeatherApiService
import com.example.weatherapp.network.WeatherNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*

const val BASE_URL="https://api.weatherapi.com/v1/"
const val KEY="3eb097b2112540a5bea132805201810"
class WeatherRepository(
    private val currentWeatherDao: CurrentWeatherDao,
    private val futureWeatherDao: FutureWeatherDao

) {

       private  val retrofit=Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
       private   val weatherApiService=retrofit.create(WeatherApiService::class.java)
       private  val weatherNetworkDataSource=WeatherNetworkDataSource(weatherApiService)


    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever {
                persistFetchedCurrentWeather(it)
        }
        weatherNetworkDataSource.downloadedFutureWeather.observeForever {
            persistFetchedFutureWeather(it)
            if(it!=null)
            Log.e("SATTU1", it.forecast.forecastday[1].date)
        }
    }


            suspend fun getCurrentWeather( metric :Boolean) : CurrentWeatherData
            {
                initWeatherData()
                Log.d("satyam", "getCurrentWeather: ")
                return currentWeatherDao.getWeatherMetric()
            }
            suspend fun getFutureWeather( metric :Boolean) : LiveData<List<Forecastday>>
            {
                initWeatherData()
                Log.d("satyam", "getCurrentWeather: ")
                return futureWeatherDao.getFutureWeather(LocalDate.now().toString())
            }

            private suspend fun initWeatherData()
            {

                fetchCurrentWeather()
                fetchFutureWeather()

            }
            private fun persistFetchedCurrentWeather(fetchedWeather:CurrentWeatherData)
            {
                GlobalScope.launch (Dispatchers.IO){
                   currentWeatherDao.upsert(fetchedWeather)
                }
            }
            private fun persistFetchedFutureWeather(fetchedWeather:futureWeatherData)
            {
                fun deleteOldForecastData() {
                    val today = LocalDate.now()
                    futureWeatherDao.deleteOldEntries(today.toString())
                }
                GlobalScope.launch (Dispatchers.IO){
//                    deleteOldForecastData()
                     val futureWeatherList=fetchedWeather.forecast.forecastday
                    futureWeatherDao.insert(futureWeatherList)

                }




            }

            private suspend fun fetchCurrentWeather()
            {
                Log.d("satyam", "Network call: ")
                weatherNetworkDataSource.fetchCurrentWeather("delhi", Locale.getDefault().language)
            }
            private suspend fun fetchFutureWeather()
            {
                Log.d("satyam", "Network call: ")
                weatherNetworkDataSource.fetchFutureWeather("delhi", Locale.getDefault().language)
            }
            private fun isFetchNeeded(lastfetchTime:ZonedDateTime):Boolean{

                val thirtyminago=ZonedDateTime.now().minusMinutes(30)
                return lastfetchTime.isBefore(thirtyminago)
            }
}