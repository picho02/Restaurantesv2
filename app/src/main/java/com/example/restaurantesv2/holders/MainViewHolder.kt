package com.example.restaurantesv2.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantesv2.clases.RestauranteResponse
import com.example.restaurantesv2.databinding.ItemRestauranteBinding
import com.example.restaurantesv2.interfaces.OnItemListener
import com.squareup.picasso.Picasso

class MainViewHolder(
    binding: ItemRestauranteBinding,
    onItemListener: OnItemListener
) : RecyclerView.ViewHolder(binding.root),  View.OnClickListener {
    private val binding = binding
    private val onItemListener = onItemListener
    private lateinit var restaurante: RestauranteResponse
    init {
        binding.root.setOnClickListener(this)
    }
    override fun onClick(p0: View?){
        onItemListener.onItemClick(restaurante)
    }
    fun render(restaurante2: RestauranteResponse){
        restaurante=restaurante2
        with(binding){
            tvNombre.text = restaurante.nombre
            tvCalificacion.text = "Calificacion: ${restaurante.calificacionPromedio}"
            tvCostoPromedio.text = "Costo promedio $ ${restaurante.costoPromedio}"
            tvFundacion.text = "Fundacion: ${restaurante.fundacion}"
        }
        Picasso.get().load(restaurante.fotos.first()).into(binding.ivItem)

    }

}