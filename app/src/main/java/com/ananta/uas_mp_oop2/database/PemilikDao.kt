package com.ananta.uas_mp_oop2.database

import androidx.room.*

@Dao
interface PemilikDao {
    @Query("SELECT * FROM pemilik ORDER BY id ASC")
    fun getPemilik(): List<Pemilik>

    @Insert
    fun insert(pemilik: Pemilik)

    @Update
    fun updatePemilik(pemilik: Pemilik)

    @Delete
    fun deletePemilik(pemilik: Pemilik)

}