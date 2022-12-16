package com.example.rentacarproyecto.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rentacarproyecto.model.Carro
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.DocumentReference

class CarroDao {
    //Firebase vars
    private var firestore: FirebaseFirestore
    private var codigoCarro: String

    init {
        codigoCarro = Firebase.auth.currentUser?.email.toString()
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }
    fun getCarros(): MutableLiveData<List<Carro>> {
        val listaCarros = MutableLiveData<List<Carro>>()
        firestore
            .collection("carros")
            .document("jeMida6W7fGdgzJtVmFa")
            .collection("caracteristicas")
            .addSnapshotListener{snapshot, e ->
                if(e != null){
                    return@addSnapshotListener
                }
                if(snapshot != null){
                    val lista = ArrayList<Carro>()
                    val carros = snapshot.documents
                    carros.forEach{
                        val carro = it.toObject(Carro::class.java)
                        if(carro != null){
                            lista.add(carro)
                        }
                    }
                    listaCarros.value = lista
                }
            }
        return listaCarros
    }

}