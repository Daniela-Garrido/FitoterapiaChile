package com.aumentarte.fitoterapiachile.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aumentarte.fitoterapiachile.Model.Local.BaseDatos.FitoBaseDatos
import com.aumentarte.fitoterapiachile.Model.Local.Entidades.DetalleFitoLocal
import com.aumentarte.fitoterapiachile.Model.Local.Entidades.ListaFitoLocal
import com.aumentarte.fitoterapiachile.Model.RepoyMapper.Repositorio
import kotlinx.coroutines.launch

class FitoViewModel (aplicacion: Application) : AndroidViewModel(aplicacion){
    //conecta con el repo
    private val repository : Repositorio
    private val detalleLiveData = MutableLiveData<DetalleFitoLocal>()
    private var seleccionado :String ="-1"

    init { //inicializa el repo, llama los datos y dao
        val bd = FitoBaseDatos.getDataBase(aplicacion)
        val dao =bd.getFitoDao()
        repository= Repositorio(dao)
        viewModelScope.launch {
            repository.fetchListaFito() //del repositorio
        }
    }
    //listado elementos

    fun getListaFito ():LiveData<List<ListaFitoLocal>> = repository.fitoLiveData

    // detalle elementos

    fun getDetalleFito ():LiveData<DetalleFitoLocal> = detalleLiveData

    //detalle por id desde inter

    fun getDetalleInter (id: Int) = viewModelScope.launch {
        val detalleFito = repository.fetchDetalle(id)
        detalleFito?.let {
            detalleLiveData.postValue(it)
        }
    }

}