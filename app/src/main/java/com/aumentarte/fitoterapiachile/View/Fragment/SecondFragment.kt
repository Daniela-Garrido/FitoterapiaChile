package com.aumentarte.fitoterapiachile.View.Fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.aumentarte.fitoterapiachile.R
import com.aumentarte.fitoterapiachile.ViewModel.FitoViewModel
import com.aumentarte.fitoterapiachile.databinding.FragmentSecondBinding
import com.bumptech.glide.Glide

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    //crear instancia viewmodel

    private val viewModel: FitoViewModel by activityViewModels()
    private var fitoId: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle -> //recibe id
            fitoId = bundle.getString("idFito") //primer fragment clave id-valor
            Log.d("Seleccion segFrament", fitoId.toString())
        }

        fitoId?.let { id ->
            viewModel.getDetalleInter(id.toInt())
        }

        //pinta la info en el 2do fragment
        viewModel.getDetalleFito().observe(viewLifecycleOwner, Observer {
            Log.d("Seleccion segFrament 2", fitoId.toString())

            var tipo = it.tipo
            var nombre = it.nombre

            Glide.with(binding.ivLogo).load(it.imagen).into(binding.ivLogo)
            binding.tvnombre.text = "Nombre: ${it.nombre}"
            binding.tvTipo.text = "Tipo: ${it.tipo}"
            binding.tvDescripcion.text = "Descripcion ${it.descripcion}"

            //enviar correo
            binding.btnMail.setOnClickListener {

                val intent = Intent(Intent.ACTION_SEND)

                intent.data = Uri.parse("mailto")
                intent.type = "text/plain"

                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("dgstudioink@gmail.com"))
                intent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Consulta por Producto " + tipo
                )
                intent.putExtra(
                    Intent.EXTRA_TEXT, "Hola Vi el producto " + nombre + " y me " +
                            "gustaría que me contactaran a este correo o al siguiente número _ Quedo atento."
                )

                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }

            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}