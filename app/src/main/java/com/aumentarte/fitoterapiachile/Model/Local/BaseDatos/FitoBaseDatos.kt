package com.aumentarte.fitoterapiachile.Model.Local.BaseDatos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aumentarte.fitoterapiachile.Model.Local.Entidades.DetalleFitoLocal
import com.aumentarte.fitoterapiachile.Model.Local.Entidades.ListaFitoLocal
import com.aumentarte.fitoterapiachile.Model.Local.FitoDao


@Database(
    entities = [ListaFitoLocal::class, DetalleFitoLocal::class], version = 1,
    exportSchema = false
)

abstract class FitoBaseDatos : RoomDatabase() {

    abstract fun getFitoDao(): FitoDao //referencia al dao

    companion object {
        @Volatile
        private var INSTANCE: FitoBaseDatos? = null

        fun getDataBase(context: Context): FitoBaseDatos {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FitoBaseDatos::class.java,
                    "CASOFUTURO"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}