package com.example.restaurantesv2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantesv2.clases.Resenia
import com.example.restaurantesv2.databinding.ItemReseniaBinding
import com.example.restaurantesv2.holders.ReseniaViewHolder

class AdapterResenia(val resenias: List<Resenia>): RecyclerView.Adapter<ReseniaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReseniaViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemReseniaBinding.inflate(inflate)
        return ReseniaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReseniaViewHolder, position: Int) {
        val resenia = resenias[position]
        holder.render(resenia)
    }

    override fun getItemCount(): Int {
        return resenias.size
    }
}