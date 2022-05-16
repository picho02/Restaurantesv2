package com.example.restaurantesv2.clases

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Resenia(@SerializedName("nombre_usuario") val usuario:String, @SerializedName("titulo_resenia") val titulo: String,
                   @SerializedName("resenia_descripcion") val resenia: String, @SerializedName("fecha_resenia") val fecha: String,
                   @SerializedName("calificacion_usuario") val calificacion: Int): Serializable
