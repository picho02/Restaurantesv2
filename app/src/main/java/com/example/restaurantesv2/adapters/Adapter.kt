package com.example.restaurantesv2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantesv2.clases.RestauranteResponse
import com.example.restaurantesv2.databinding.ItemRestauranteBinding
import com.example.restaurantesv2.holders.MainViewHolder
import com.example.restaurantesv2.interfaces.OnItemListener

class Adapter(val  restaurantes: List<RestauranteResponse>, val onItemListener: OnItemListener):
    RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemRestauranteBinding.inflate(inflate)
        return MainViewHolder(binding,onItemListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val restaurant: RestauranteResponse = restaurantes.get(position)
        holder.render(restaurant)
    }

    override fun getItemCount(): Int {
        return restaurantes.size
    }
}