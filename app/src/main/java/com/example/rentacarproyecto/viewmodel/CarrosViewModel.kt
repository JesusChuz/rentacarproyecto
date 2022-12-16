package com.example.rentacarproyecto.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rentacarproyecto.data.CarroDao
import com.example.rentacarproyecto.model.Carro
import com.example.rentacarproyecto.repository.CarroRepository
import kotlinx.coroutines.launch

class CarrosViewModel(application: Application) : AndroidViewModel(application) {

    val obtenerLugares: MutableLiveData<List<Carro>>
    private val repository: CarroRepository = CarroRepository(CarroDao())

    init {
        obtenerLugares = repository.getCarros
    }

}