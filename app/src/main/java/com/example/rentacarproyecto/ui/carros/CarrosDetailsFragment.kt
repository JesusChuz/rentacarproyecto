package com.example.rentacarproyecto.ui.carros

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.rentacarproyecto.R
import androidx.navigation.fragment.findNavController
import com.example.rentacarproyecto.adapter.CarroAdapter
import com.example.rentacarproyecto.databinding.FragmentCarrosDetailsBinding
import com.example.rentacarproyecto.model.Carro
import com.example.rentacarproyecto.viewmodel.CarrosViewModel


class CarrosDetailsFragment: Fragment() {
    //recuperar los elementos enviados
    private val args by navArgs<CarrosDetailsFragmentArgs>()

    private var _binding: FragmentCarrosDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var lugarViewModel: CarrosViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lugarViewModel = ViewModelProvider(this).get(CarrosViewModel::class.java)
        _binding = FragmentCarrosDetailsBinding.inflate(inflater, container, false)

        // carga de datos de lugar
        binding.tvModelo.setText(args.carroArgs.modelo)
        binding.lblPrecio.setText(args.carroArgs.precio)
        binding.lblMarcha.setText(args.carroArgs.marchas)
        binding.lblPasajeros.setText(args.carroArgs.pasajeros)
        binding.lblPuertas.setText(args.carroArgs.puertas)
        binding.lblaire.setText(args.carroArgs.aire)

//        binding.btUpdateLugar.setOnClickListener { updateLugar() }
        //      binding.btDeleteLugar.setOnClickListener { deleteLugar() }

        // setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return binding.root
    }
}