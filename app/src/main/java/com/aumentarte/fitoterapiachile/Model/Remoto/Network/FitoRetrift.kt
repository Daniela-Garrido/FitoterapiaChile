package com.aumentarte.fitoterapiachile.Model.Remoto.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FitoRetrift {
    companion object {
        private const val BASE_URL = "https://my-json-server.typicode.com/mauricioponce/TDApi/"
        lateinit var retrofitInstance: Retrofit

        fun retrofitInstance(): FitoApi{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(FitoApi::class.java)
        }
    }
}