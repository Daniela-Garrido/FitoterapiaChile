package com.aumentarte.fitoterapiachile.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.aumentarte.fitoterapiachile.Model.Local.Entidades.ListaFitoLocal
import com.aumentarte.fitoterapiachile.databinding.DisenoCardBinding
import com.bumptech.glide.Glide

class FitoAdapter : RecyclerView.Adapter<FitoAdapter.CardViewHolder>() {

    private var listafitoAdapter = listOf<ListaFitoLocal>()
    private val selectList = MutableLiveData<ListaFitoLocal>()


    //actualizar el adapter
    fun actualizar (list : List<ListaFitoLocal>){
        listafitoAdapter = list
        notifyDataSetChanged()
    }

    //seleccionar un cardview
    fun selectedCard (): LiveData<ListaFitoLocal> = selectList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(DisenoCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listafitoAdapter.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val lista = listafitoAdapter[position]
        holder.bin(lista)

    }

    inner class CardViewHolder(private val binding: DisenoCardBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {


        fun bin(listaFito: ListaFitoLocal) {

            Glide.with(binding.ivLogo).load(listaFito.imagen).centerCrop().into(binding.ivLogo)
            binding.tvname.text = listaFito.nombre
            binding.tvtipo.text = listaFito.tipo
            binding.tvdescripcion.text = listaFito.descripcion
            itemView.setOnClickListener(this) //escuchador de click

        }

        override fun onClick(v: View?) {
            selectList.value = listafitoAdapter[adapterPosition] //te toma la tarjeta en su posicion

        }

    }

}