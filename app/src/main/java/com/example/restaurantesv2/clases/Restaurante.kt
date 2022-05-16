package com.example.restaurantesv2.clases

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Restaurante(@SerializedName("restaurantes") val restaurantes: List<RestauranteResponse>): Serializable
