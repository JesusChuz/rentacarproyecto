package com.example.rentacarproyecto.data

import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rentacarproyecto.model.Carro
import com.example.rentacarproyecto.model.Usuario
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.DocumentReference

class UsuarioDao {
    //Firebase vars
    private var firestore: FirebaseFirestore
    private var codigoUsuario: String
    init {
        codigoUsuario = Firebase.auth.currentUser?.email.toString()
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }
    fun addUsuario(usuario : Usuario){
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

}