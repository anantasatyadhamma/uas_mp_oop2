package com.ananta.uas_mp_oop2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Hewan::class, Pemilik::class), version = 1, exportSchema = false)
public abstract class AdopsiDB : RoomDatabase() {

    abstract fun hewanDao(): HewanDao
    abstract fun pemilikDao(): PemilikDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AdopsiDB? = null

        fun getDatabase(context: Context): AdopsiDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    AdopsiDB::class.java,
                    "adopsi.db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}