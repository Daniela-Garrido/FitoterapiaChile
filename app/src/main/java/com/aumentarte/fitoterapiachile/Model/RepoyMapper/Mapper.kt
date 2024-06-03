package com.aumentarte.fitoterapiachile.Model.RepoyMapper

import com.aumentarte.fitoterapiachile.Model.Local.Entidades.DetalleFitoLocal
import com.aumentarte.fitoterapiachile.Model.Local.Entidades.ListaFitoLocal
import com.aumentarte.fitoterapiachile.Model.Remoto.Internet.DetalleFitoInter
import com.aumentarte.fitoterapiachile.Model.Remoto.Internet.ListaFitoInter

fun fromInterLista(Lista: List<ListaFitoInter>): List<ListaFitoLocal> {
    return Lista.map {

        ListaFitoLocal(

            id = it.id,
            nombre = it.nombre,
            tipo = it.tipo,
            imagen = it.imagen,
            descripcion = it.descripcion

        )
    }
}

    fun fromInterDetalle(detail: DetalleFitoInter): DetalleFitoLocal {
        return DetalleFitoLocal(

            id = detail.id,
            nombre = detail.nombre,
            tipo = detail.tipo,
            imagen = detail.imagen,
            descripcion = detail.descripcion
        )
    }