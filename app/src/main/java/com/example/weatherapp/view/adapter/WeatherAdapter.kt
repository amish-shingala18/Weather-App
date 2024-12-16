package com.example.weatherapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.MainActivity
import com.example.weatherapp.R
import com.example.weatherapp.data.model.ListItem
import com.example.weatherapp.databinding.WeatherItemBinding
import com.example.weatherapp.viewmodel.WeatherViewModel

class WeatherAdapter(private val list: List<ListItem?>, private val weatherViewModel: WeatherViewModel)
    : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = WeatherItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item,parent,false)
        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        if(position%2==0){
            holder.binding.cvItem.setCardBackgroundColor(holder.itemView.context.getColor(R.color.even))
        }else{
            holder.binding.cvItem.setCardBackgroundColor(holder.itemView.context.getColor(R.color.odd))
        }
        weatherViewModel.weatherLiveData.observe(holder.itemView.context as MainActivity) {
            holder.binding.txtItemDegree.text =
                "${String.format("%.1f", it[position]?.main?.temp)} \u00B0"
            val dateTime = it[position]?.dtTxt
            holder.binding.txtItemtime.text = dateTime?.split(" ")?.get(1)?.substring(0, 5)
            val main = it[position]?.weather?.get(0)?.main
            when (main) {
                "Clear" -> {
                    holder.binding.imgItem.setImageResource(R.drawable.clear_sky)
                }
                "Clouds" -> {
                    holder.binding.imgItem.setImageResource(R.drawable.cloudy)
                }
                "Rain" -> {
                    holder.binding.imgItem.setImageResource(R.drawable.rain)
                }
                "Snow" -> {
                    holder.binding.imgItem.setImageResource(R.drawable.snowman)
                }
                "Drizzle" -> {
                    holder.binding.imgItem.setImageResource(R.drawable.drizzle)
                }
                "Thunderstorm" -> {
                    holder.binding.imgItem.setImageResource(R.drawable.thunderstorm)
                }
                else -> {
                    holder.binding.imgItem.setImageResource(R.drawable.atmospheric_conditions)
                }
            }
        }
    }
}