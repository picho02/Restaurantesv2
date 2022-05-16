package com.example.restaurantesv2.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantesv2.clases.Resenia
import com.example.restaurantesv2.databinding.ItemReseniaBinding

class ReseniaViewHolder (binding: ItemReseniaBinding) : RecyclerView.ViewHolder(binding.root) {
    private val binding = binding
    private lateinit var resenia: Resenia
    fun render(resenia: Resenia) {
        this.resenia = resenia
        binding.tvUsuarioResenia.text = resenia.usuario
        binding.tvFechayCal.text = "Cal: ${resenia.calificacion}     Fecha: ${resenia.fecha}"
        binding.tvTituloRes.text = resenia.titulo
        binding.tvResenia.text = resenia.resenia


    }
}