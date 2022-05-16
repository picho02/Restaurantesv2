package com.example.restaurantesv2.vistas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantesv2.clases.Usuario
import com.example.restaurantesv2.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val usuarios = mutableListOf<Usuario>()
    lateinit var autenticacion: FirebaseAuth
    private var usuarioIngresado = ""
    private var pswIngresado = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        autenticacion = FirebaseAuth.getInstance()

    }


    override fun onStart() {
        super.onStart()
        binding.btnRegistrar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {

            usuarioIngresado = binding.etUser.text.toString()
            pswIngresado = binding.etPwd.text.toString()
            valorar()

        }
    }


    /*private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://demo1416549.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun search() {

        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<UsuarioResponse> =
                getRetrofit().create(APIService::class.java).getUsuarios("usuarios2")
            val usuario: UsuarioResponse? = call.body()
            val tmpUsuario: List<Usuario> = usuario?.usuarios ?: emptyList()
            runOnUiThread { //Para corre en el hiloprincipal
                if (call.isSuccessful) {

                    if (usuarios != null) {
                        usuarios.clear()
                        usuarios.addAll(tmpUsuario)
                    }

                } else {
                    //Error, Manejo del error
                    showError()
                }


            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
    }*/

    fun valorar() {
        if (usuarioIngresado == "" || pswIngresado == "") {
            Toast.makeText(this, "ingrese todos los datos", Toast.LENGTH_LONG).show()
        } else {

            autenticacion.signInWithEmailAndPassword(usuarioIngresado, pswIngresado)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        //avanzar a la pantalla principal
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        //mostrar resultado de error
                        Toast.makeText(this, "Algo salio mal", Toast.LENGTH_LONG)
                            .show()
                    }
                }

        }
    }
}