package com.example.rentacarproyecto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rentacarproyecto.databinding.CarrosFilaBinding
import com.example.rentacarproyecto.model.Carro
import com.example.rentacarproyecto.ui.carros.CarrosDetailsFragment
import com.example.rentacarproyecto.ui.carros.CarrosFragmentDirections

class CarroAdapter: RecyclerView.Adapter<CarroAdapter.CarrosViewHolder>() {
    //Lista de Carros
    private var listaCarros = emptyList<Carro>()

    fun setCarros(carros: List<Carro>){
        listaCarros= carros
        notifyDataSetChanged()
    }

    inner class CarrosViewHolder(private val itemBinding: CarrosFilaBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun setear(carro: Carro) {
            itemBinding.tvModelo.text = carro.modelo
            //itemBinding.ivImagen. = carro.img
            itemBinding.tvPrecio.text = carro.precio
            itemBinding.tvPasajeros.text = carro.pasajeros
            itemBinding.tvPuertas.text = carro.puertas
            itemBinding.tvMarchas.text = carro.marchas
            itemBinding.tvAire.text = carro.aire

            itemBinding.vistaFila.setOnClickListener {
                val action = CarrosFragmentDirections.actionNavCarrosToNavCarrosDetails(carro)
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrosViewHolder {
        val itemBinding =
            CarrosFilaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarrosViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CarrosViewHolder, position: Int) {
        val lugar = listaCarros[position]
        holder.setear(lugar)
    }

    override fun getItemCount(): Int {
        return listaCarros.size
    }
}