package com.example.restaurantesv2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantesv2.databinding.ItemFotoBinding
import com.example.restaurantesv2.holders.FotoViewHolder

class AdapterFotos (val fotos: List<String>): RecyclerView.Adapter<FotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotoViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemFotoBinding.inflate(inflate)
        return FotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FotoViewHolder, position: Int) {
        val foto = fotos[position]
        holder.render(foto)
    }

    override fun getItemCount(): Int {
        return fotos.size
    }
}