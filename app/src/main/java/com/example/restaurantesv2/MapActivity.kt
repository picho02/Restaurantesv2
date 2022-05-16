package com.example.restaurantesv2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.restaurantesv2.clases.RestauranteResponse
import com.example.restaurantesv2.vistas.DetalleActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    lateinit var restaurante : RestauranteResponse

    companion object{
        const val REQUEST_CODE_LOCATION=0

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        createFragment()
        val bundle = intent.extras
        restaurante = bundle?.getSerializable("restaurante") as RestauranteResponse

    }

    private fun enableLocation(){
        if(!::map.isInitialized)return
        if(isLocationPermissionGranted())
        {
            map.isMyLocationEnabled=true
        }else{
            requestLocationPermission()
        }
    }


    private fun  createFragment(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMapReady(googleMap: GoogleMap) {
        //Este codigo se ejecutara cuando el fregment termine de cargarse

        map = googleMap
        if (restaurante!=null){
createMarker(restaurante.latitud,restaurante.longitud,restaurante.nombre)
        }
        findViewById<Button>(R.id.btnRestaurante1).setOnClickListener {
            val intent = Intent(this, DetalleActivity::class.java)

            val parametros = Bundle()
            parametros.putSerializable("restaurante",restaurante)
            intent.putExtras(parametros)
            startActivity(intent)
        }

        //tratar de acceder a la ubicacion del GPS
        enableLocation()


    }

    private fun isLocationPermissionGranted()=
        ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            //Mostrar la ventan de permiso        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            REQUEST_CODE_LOCATION->if (grantResults.isNotEmpty()&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                map.isMyLocationEnabled=true
            }
            else{
                Toast.makeText(this,"Activa tus servicios manualmente", Toast.LENGTH_LONG).show()
            }
            else->{}

        }
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    private fun createMarker(latitud: Double,longitud:Double,nombre:String) {
        val coordinates = LatLng(latitud, longitud)
        val marker= MarkerOptions().position(coordinates).title(nombre)
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates,18f),
            4000,null
        )

    }

}