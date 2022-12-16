package com.example.rentacarproyecto.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Carro(
    var id: String,
    val img: String,
    val modelo: String,
    val precio: String?,
    val pasajeros: String?,
    val puertas: String?,
    val marchas: String?,
    val aire: String?
) : Parcelable {
    constructor():
        this("","","","","", "", "", "")
}

