package com.example.restaurantesv2.interfaces


import com.example.restaurantesv2.clases.Restaurante
import com.example.restaurantesv2.clases.UsuarioResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getRestaurant(@Url url:String): Response<Restaurante>
    @GET
    suspend fun getUsuarios(@Url url: String): Response<UsuarioResponse>
}