package com.example.restaurantesv2.vistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.restaurantesv2.adapters.Adapter
import com.example.restaurantesv2.R
import com.example.restaurantesv2.clases.Restaurante
import com.example.restaurantesv2.clases.RestauranteResponse
import com.example.restaurantesv2.databinding.ActivityMainBinding
import com.example.restaurantesv2.interfaces.APIService
import com.example.restaurantesv2.interfaces.OnItemListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnItemListener {
    private lateinit var binding: ActivityMainBinding
    private val restaurantes = mutableListOf<RestauranteResponse>()
    private lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        search()
    }
    fun initRecycler(){
        adapter = Adapter(restaurantes,this)
        binding.rvRestaurantes.layoutManager = LinearLayoutManager(this)
        binding.rvRestaurantes.adapter = adapter
    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://demo1416549.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    public fun search (){
        //Toast.makeText(this@MainActivity,"buscando..", Toast.LENGTH_LONG).show()
        //Pasar a hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<Restaurante> = getRetrofit().create(APIService::class.java).getRestaurant("listarestaurantes2")
            val restaurant: Restaurante? =call.body()
            val tmpRestaurante: List<RestauranteResponse> = restaurant?.restaurantes ?: emptyList()
            runOnUiThread { //Para corre en el hiloprincipal
                if (call.isSuccessful){

                    if (restaurant!=null){
                        restaurantes.clear()
                        restaurantes.addAll(tmpRestaurante)
                        adapter.notifyDataSetChanged()
                    }

                }else{
                    //Error, Manejo del error
                    showError()
                }

            }
        }
    }
    private fun showError(){
        Toast.makeText(this,"error", Toast.LENGTH_LONG).show()}

    override fun onItemClick(restaurante: RestauranteResponse) {
        val intent = Intent(this, DetalleActivity::class.java)

        val parametros = Bundle()
        parametros.putSerializable("restaurante",restaurante)
        intent.putExtras(parametros)
        startActivity(intent)
    }
}