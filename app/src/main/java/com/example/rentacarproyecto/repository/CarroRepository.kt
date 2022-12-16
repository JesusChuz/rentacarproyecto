package com.example.rentacarproyecto.repository

import androidx.lifecycle.MutableLiveData
import com.example.rentacarproyecto.data.CarroDao
import com.example.rentacarproyecto.model.Carro

class CarroRepository(private val carroDao: CarroDao) {
    val getCarros: MutableLiveData<List<Carro>> = carroDao.getCarros()
}