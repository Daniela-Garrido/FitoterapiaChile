package com.aumentarte.fitoterapiachile.Model.Remoto.Network

import com.aumentarte.fitoterapiachile.Model.Remoto.Internet.DetalleFitoInter
import com.aumentarte.fitoterapiachile.Model.Remoto.Internet.ListaFitoInter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FitoApi {

    @GET("plantas")
    suspend fun fetchListaFitoInter() : Response<List<ListaFitoInter>> //lo de inter

    @GET("plantas/{id}")
    suspend fun fetchDetalleFitoInter(@Path("id")id:Int): Response<DetalleFitoInter>
}