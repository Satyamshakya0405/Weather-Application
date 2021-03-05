package com.example.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.viewmodels.DetailFutureViewModel
import com.example.weatherapp.R

class DetailFutureFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFutureFragment()
    }

    private lateinit var viewModel: DetailFutureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_future_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailFutureViewModel::class.java)
        // TODO: Use the ViewModel
    }

}