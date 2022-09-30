package com.ramadan.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.ramadan.weatherapp.R
import com.ramadan.weatherapp.core.common.AppConst.HTTPS
import com.ramadan.weatherapp.core.common.AppConst.MPH
import com.ramadan.weatherapp.core.common.AppConst.PERCENT
import com.ramadan.weatherapp.data.response.WeatherResponse
import com.ramadan.weatherapp.databinding.FragmentCurrentWeatherBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFragment : Fragment() {
    private lateinit var binding : FragmentCurrentWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentWeatherBinding.inflate(inflater,container,false)
        attachObserver()
        return binding.root
    }

   private  fun attachObserver(){
      viewModel.weather_forecast.observe(viewLifecycleOwner){
          when(it){
              is DataState.Success -> {
                  hideLoading()
                  binding.mainView.visibility = View.VISIBLE
                  setData(it.data)
              }
              is DataState.Error -> {
                  binding.mainView.visibility = View.GONE
                  hideLoading()
                  Toast.makeText(requireContext() , getString(R.string.error) , Toast.LENGTH_SHORT).show()
              }
              is DataState.Loading -> {
                  binding.mainView.visibility = View.GONE
                  showLoading()
              }
              else -> {}
          }
      }
    }

    private fun setData(data: WeatherResponse) {
        val weatherStatusUrl = HTTPS.plus(data.current.condition.icon)
        binding.time.text = viewModel.formatHour(data.location.localtime)
        binding.city.text = data.location.name
        binding.day.text = viewModel.formatData(data.location.localtime)
        binding.degree.text = data.current.temp_f.toString().plus(getString(R.string.feh))
        Picasso.get().load(weatherStatusUrl).into(binding.weatherImage)
        binding.weatherStatus.text = data.current.condition.text
        binding.windSpeed.text = data.current.wind_mph.toString().plus(MPH)
        binding.dropletPercentage.text = data.current.humidity.toString().plus(PERCENT)
        //forecast days
        binding.todayDegree.text = data.forecast.forecastday[0].day.mintemp_f.toString().plus("/${data.forecast.forecastday[0].day.maxtemp_f}").plus(getString(R.string.feh))
        Picasso.get().load(HTTPS.plus(data.forecast.forecastday[0].day.condition.icon)).into(binding.todayIcon)
        binding.tomorrowDegree.text = data.forecast.forecastday[1].day.mintemp_f.toString().plus("/${data.forecast.forecastday[1].day.maxtemp_f}").plus(getString(R.string.feh))
        Picasso.get().load(HTTPS.plus(data.forecast.forecastday[1].day.condition.icon)).into(binding.tomorrowIcon)
        binding.friDegree.text = data.forecast.forecastday[2].day.mintemp_f.toString().plus("/${data.forecast.forecastday[2].day.maxtemp_f}").plus(getString(R.string.feh))
        Picasso.get().load(HTTPS.plus(data.forecast.forecastday[2].day.condition.icon)).into(binding.friIcon)
    }

    private fun showLoading(){
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun hideLoading(){
        binding.progressbar.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        viewModel.getWeatherFor("san francisco")
    }


}