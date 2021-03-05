package com.example.weatherapp.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.weatherapp.Repository.WeatherRepository
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController= Navigation.findNavController(this, R.id.nav_host_fragment)
        bottom_Navigation_View.setupWithNavController(navController)
//
//        NavigationUI.setupActionBarWithNavController(this,navController)
    }



}