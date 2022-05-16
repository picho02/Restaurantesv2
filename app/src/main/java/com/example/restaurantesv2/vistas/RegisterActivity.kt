package com.example.restaurantesv2.vistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.restaurantesv2.R
import com.example.restaurantesv2.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    lateinit var autenticacion: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        autenticacion = FirebaseAuth.getInstance()
        binding.btnCancelarRegister.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnAceptarRegister.setOnClickListener {
            val nombre: String = binding.etName.text.toString()
            val apellido: String = binding.etApellido.text.toString()
            val correo: String = binding.etEmailRegister.text.toString()
            val password: String = binding.etPswRegister.text.toString()
            autenticacion.createUserWithEmailAndPassword(correo, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    db.collection("user").document(autenticacion.currentUser.toString()).set(
                        hashMapOf( "nombre" to nombre, "apellido" to apellido, "email" to correo, "password" to password)
                    )
                    //avanzar a la pantalla principal
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    //mostrar resultado de error
                    Toast.makeText(this,"Algo salio mal", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}