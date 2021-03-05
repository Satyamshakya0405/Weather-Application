package com.example.weatherapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.db.CurrentWeatherModelClass.CurrentWeatherData
import com.example.weatherapp.db.FutureWeatherModelClass.Forecastday
import com.example.weatherapp.fragments.CurrentWeatherFragment

@Database(entities = [CurrentWeatherData::class , Forecastday::class],version = 2,exportSchema = false)
abstract class ForecastDatabase :RoomDatabase(){

    abstract fun currentWeatherDao():CurrentWeatherDao
    abstract fun futureWeatherDao():FutureWeatherDao

    companion object{
        @Volatile
        private var INSTANCE:ForecastDatabase?=null

        fun getForecastDatabase(context: Context):ForecastDatabase
        {
            val tempInstance= INSTANCE
            if(tempInstance!=null) return tempInstance

            synchronized(this)
            {
                val instance= Room.databaseBuilder(context.applicationContext,ForecastDatabase::class.java
                ,"Current_Weather").build()

                INSTANCE=instance
                return instance
            }

        }



    }

}