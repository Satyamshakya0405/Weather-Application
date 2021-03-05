package com.example.weatherapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weatherapp.Repository.WeatherRepository
import com.example.weatherapp.viewmodels.CurrentWeatherViewModel
import com.example.weatherapp.R
import com.example.weatherapp.Repository.BASE_URL
import com.example.weatherapp.db.ForecastDatabase
import com.example.weatherapp.network.WeatherApiService
import com.example.weatherapp.network.WeatherNetworkDataSource
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CurrentWeatherFragment : Fragment(R.layout.current_weather_fragment) {


    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
        // TODO: Use the ViewModel

       viewModel.getdata()
        viewModel.mutableLivedata.observe(this.viewLifecycleOwner, Observer {
//            txtview.text=it.toString()
                group_loading.visibility=View.GONE
            updateDateToToday()
            updateLocation(it.location.name)
            updateTemperatures(it.current.temp_c, it.current.feelslike_c)
            updateCondition(it.current.condition.text)
            updatePrecipitation(it.current.precip_mm)
            updateWind(it.current.wind_dir, it.current.wind_kph)
            updateVisibility(it.current.vis_km)

                Glide.with(this).load("https:${it.current.condition.icon}").into(imageView_condition_icon)

        })

    }
    private fun updateDateToToday() {

        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }
    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }
    private fun updateTemperatures(temperature: Double, feelsLike: Double) {

        textView_temperature.text = "$temperature°C"
        textView_feels_like_temperature.text = "Feels like $feelsLike°C"
    }
    private fun updateCondition(condition: String) {
        textView_condition.text = condition
    }
    private fun updatePrecipitation(precipitationVolume: Double) {

        textView_precipitation.text = "Preciptiation: $precipitationVolume mm"
    }
    private fun updateWind(windDirection: String, windSpeed: Double) {

        textView_wind.text = "Wind: $windDirection, $windSpeed  kph"
    }
    private fun updateVisibility(visibilityDistance: Double) {

        textView_visibility.text = "Visibility: $visibilityDistance km"
    }
}