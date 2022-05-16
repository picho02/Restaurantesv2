package com.example.restaurantesv2.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantesv2.databinding.ItemFotoBinding
import com.squareup.picasso.Picasso

class FotoViewHolder(binding: ItemFotoBinding) : RecyclerView.ViewHolder(binding.root) {
    private val binding = binding
    private lateinit var foto: String
    fun render(foto: String) {
        this.foto = foto
        Picasso.get().load(foto).into(binding.ivFotoRecycler)

    }
}