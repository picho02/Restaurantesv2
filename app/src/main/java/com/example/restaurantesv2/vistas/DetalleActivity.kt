package com.example.restaurantesv2.vistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantesv2.MapActivity
import com.example.restaurantesv2.adapters.AdapterFotos
import com.example.restaurantesv2.adapters.AdapterResenia
import com.example.restaurantesv2.clases.RestauranteResponse
import com.example.restaurantesv2.databinding.ActivityDetalleBinding
import com.squareup.picasso.Picasso

class DetalleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        val restaurante = bundle?.getSerializable("restaurante") as RestauranteResponse
        if (restaurante!=null){
            binding.tvNombreDetalle.text = restaurante.nombre
            binding.tvCalyCost.text = "Cal: ${restaurante.calificacionPromedio}    $ ${restaurante.costoPromedio}   Inagurado: ${restaurante.fundacion}"
            binding.tvDireccion.text = restaurante.direccion
            val adapterFotos = AdapterFotos(restaurante.fotos)
            Picasso.get().load(restaurante.fotos.first()).into(binding.ivPrincipal)
            binding.rvImagenes.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)
            binding.rvImagenes.adapter = adapterFotos
            val adapterResenia = AdapterResenia(restaurante.resenia)
            binding.rvResenia.layoutManager = LinearLayoutManager(this)
            binding.rvResenia.adapter = adapterResenia
            binding.ibUbicacion.setOnClickListener{
                val intent = Intent(this, MapActivity::class.java)

                val parametros = Bundle()
                parametros.putSerializable("restaurante",restaurante)
                intent.putExtras(parametros)
                startActivity(intent)
            }
            binding.ibFlecha.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }
}