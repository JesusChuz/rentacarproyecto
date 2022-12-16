package com.example.rentacarproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rentacarproyecto.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    //Objeto Firebase Auth
    private lateinit var auth : FirebaseAuth
    //Pantalla xml
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializar Auth
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        //Click Registro
        binding.btnRegistro.setOnClickListener{ moverRegistro() }
        //Click login
        binding.btnIniciar.setOnClickListener{ login() }
    }
    private fun moverRegistro(){
        val intent = Intent(this,RegistroActivity::class.java)
        startActivity(intent)
    }
    private fun cargarPantalla(user: FirebaseUser?){
        if(user!=null){
             val intent = Intent(this,Feedcarros::class.java)
            startActivity(intent)
        }
    }
    private fun login(){
        val email = binding.txtCorreo.text.toString();
        val clave: String=binding.txtPassword.text.toString();

        //Login
        auth.signInWithEmailAndPassword(email, clave)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    val user = auth.currentUser
                    cargarPantalla(user)
                } else {
                    Toast.makeText(baseContext, getText(R.string.noLogin), Toast.LENGTH_LONG).show()
                }
            }
    }
    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        cargarPantalla(user)
    }
}