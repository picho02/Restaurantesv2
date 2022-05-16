package com.example.restaurantesv2.interfaces


import com.example.restaurantesv2.clases.RestauranteResponse

interface OnItemListener {
    fun onItemClick(restaurante: RestauranteResponse)
}