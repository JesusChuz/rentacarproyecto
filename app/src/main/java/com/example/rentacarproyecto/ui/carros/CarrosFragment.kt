package com.example.rentacarproyecto.ui.carros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rentacarproyecto.adapter.CarroAdapter
import com.example.rentacarproyecto.databinding.FragmentCarrosBinding
import com.example.rentacarproyecto.viewmodel.CarrosViewModel

class CarrosFragment : Fragment() {
    private lateinit var carroViewModel: CarrosViewModel
    private var _binding: FragmentCarrosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        carroViewModel = ViewModelProvider(this).get(CarrosViewModel::class.java)
        _binding = FragmentCarrosBinding.inflate(inflater, container, false)

        //Listado lugares | activar el recyclerview
        val carroAdapter = CarroAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = carroAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        carroViewModel = ViewModelProvider(this)[CarrosViewModel::class.java]

        carroViewModel.obtenerLugares.observe(viewLifecycleOwner){
                carros -> carroAdapter.setCarros(carros)
            //lugarAdapter.setLugars(it)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}