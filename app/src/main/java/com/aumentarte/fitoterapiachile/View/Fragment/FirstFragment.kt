package com.aumentarte.fitoterapiachile.View.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aumentarte.fitoterapiachile.R
import com.aumentarte.fitoterapiachile.ViewModel.FitoAdapter
import com.aumentarte.fitoterapiachile.ViewModel.FitoViewModel
import com.aumentarte.fitoterapiachile.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    //crear variable viewmodel

    private val viewModel : FitoViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FitoAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        //observa cambios FitoViewModel
        viewModel.getListaFito().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("Listado", it.toString())
                adapter.actualizar(it)
            }
        })

        //seleccionar un card FitoAdapter
        adapter.selectedCard().observe(viewLifecycleOwner, Observer {
            it.let {
                Log.d("Seleccionado", it.toString())
            }
            //pasa info al 2do fragmento (id)
            val bundle = Bundle().apply {
                putString("idFito", it.id.toString())
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}