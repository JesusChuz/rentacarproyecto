package com.example.rentacarproyecto.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Usuario
    (
    var id: String,
    val nombre: String,
    val email: String,
    val telefono: String?
) : Parcelable {
constructor():
    this("","","","")
}