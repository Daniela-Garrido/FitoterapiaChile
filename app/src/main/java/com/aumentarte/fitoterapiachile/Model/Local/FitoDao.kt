package com.aumentarte.fitoterapiachile.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aumentarte.fitoterapiachile.Model.Local.Entidades.DetalleFitoLocal
import com.aumentarte.fitoterapiachile.Model.Local.Entidades.ListaFitoLocal

@Dao
interface FitoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAll(ListaFito : List<ListaFitoLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserDetail(detalle: DetalleFitoLocal)

    @Query("SELECT * FROM list_table ORDER BY id ASC")
    fun getInsertAll() :LiveData<List<ListaFitoLocal>>

    @Query("SELECT * FROM detail_table WHERE id = :id")
    fun getDetail(id: Int) : LiveData<DetalleFitoLocal>

}