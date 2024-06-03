package com.aumentarte.fitoterapiachile.Model.Local.Entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_table")
data class ListaFitoLocal(

    @PrimaryKey
    val id: Int,
    val nombre: String,
    val tipo: String,
    val imagen: String,
    val descripcion: String
)