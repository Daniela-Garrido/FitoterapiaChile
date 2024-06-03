package com.aumentarte.fitoterapiachile.Model.RepoyMapper

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.aumentarte.fitoterapiachile.Model.Local.BaseDatos.FitoBaseDatos
import com.aumentarte.fitoterapiachile.Model.Local.Entidades.DetalleFitoLocal
import com.aumentarte.fitoterapiachile.Model.Local.Entidades.ListaFitoLocal
import com.aumentarte.fitoterapiachile.Model.Local.FitoDao
import com.aumentarte.fitoterapiachile.Model.Remoto.Internet.DetalleFitoInter
import com.aumentarte.fitoterapiachile.Model.Remoto.Network.FitoRetrift

class Repositorio(private val fitoDao : FitoDao) {

    private val netService = FitoRetrift.retrofitInstance()
    val fitoLiveData = fitoDao.getInsertAll()
    val listaFito = MutableLiveData<ListaFitoLocal>()
    val detallefito = MutableLiveData<DetalleFitoLocal>()

    suspend fun fetchListaFito() {

        val service = kotlin.runCatching { netService.fetchListaFitoInter() } //fetch de la api
        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {
                    Log.d("cursos", it.toString())
                    fitoDao.inserAll(fromInterLista(it))
                }

                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }

        }
    }

    suspend fun fetchDetalle(id: Int): DetalleFitoLocal? {
        val service = kotlin.runCatching { netService.fetchDetalleFitoInter(id)} //fetch de la api
        return service.getOrNull()?.body()?.let {DetalleFitoInter -> //detalle inter

            val detalleFitoLocal = fromInterDetalle(DetalleFitoInter) //from mapper
            fitoDao.inserDetail(detalleFitoLocal)
            detalleFitoLocal

        }

        }

    }
