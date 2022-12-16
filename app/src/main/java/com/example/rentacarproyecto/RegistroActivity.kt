package com.example.rentacarproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rentacarproyecto.databinding.ActivityRegistroBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import androidx.lifecycle.MutableLiveData
import com.example.rentacarproyecto.model.Carro
import com.example.rentacarproyecto.model.Usuario


class RegistroActivity : AppCompatActivity() {
    //Objeto Firebase Auth
    private lateinit var auth : FirebaseAuth
    //Pantalla xml
    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializar Auth
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        //Click Registro
        binding.btnRegistrarse.setOnClickListener{ registrar() }
        //Click Login
        binding.btnIniciar.setOnClickListener{ moverLogin() }
    }
    //Firebase vars
    private var firestore: FirebaseFirestore
    private var codigoUsuario: String
    init {
        codigoUsuario = Firebase.auth.currentUser?.email.toString()
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }
    private fun registrar(){
        val nombre = binding.txtNombre.text.toString();
        val identificacion: String=binding.txtIdentificacion.text.toString();
        val email: String=binding.txtCorreo.text.toString();
        val clave: String=binding.txtPasswordRegistro.text.toString();
        val telefono: String=binding.txtTelefono.text.toString();

        //Registro Usuario
        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    val user = auth.currentUser
                    cargarPantalla(user)
                }else{
                    Toast.makeText(baseContext, "Fallo ${task.exception.toString()}", Toast.LENGTH_LONG).show()
                }
            }
        if(nombre.isNotEmpty()){
            val usuario = Usuario("", nombre, email, telefono)
            insertarUsuario(usuario)
            Toast.makeText(baseContext, "Registrado", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(baseContext, "Faltan datos", Toast.LENGTH_LONG).show()
        }


    }
    private fun insertarUsuario(usuario : Usuario){
        val document: DocumentReference
        if(usuario.id.isEmpty()){
            //Agregar
            document = firestore
                .collection("usuarios")
                .document(codigoUsuario)
                .collection("datos")
                .document()
            usuario.id = document.id
        }else{
            //Modificar
            document = firestore
                .collection("usuarios")
                .document(codigoUsuario)
                .collection("datos")
                .document(usuario.id)
        }
        document.set(usuario)
            .addOnCompleteListener{
                Log.d("guardarUsuario", "Registrado con exito")
            }
            .addOnCompleteListener{
                Log.d("guardarUsuario", "Error al guardar")
            }
    }
    private fun moverLogin(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    private fun cargarPantalla(user: FirebaseUser?){
        if(user!=null){
            val intent = Intent(this,Feedcarros::class.java)
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        cargarPantalla(user)
    }
}