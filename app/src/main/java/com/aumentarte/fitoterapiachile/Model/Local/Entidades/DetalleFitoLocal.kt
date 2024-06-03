package com.aumentarte.fitoterapiachile.Model.Local.Entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_table")
data class DetalleFitoLocal(

    @PrimaryKey
    val id: Int,
    val nombre: String,
    val tipo: String,
    val imagen: String,
    val descripcion: String
)