package com.ananta.uas_mp_oop2.database

import androidx.room.*

@Dao
interface PemilikDao {
    @Query("SELECT * FROM pemilik ORDER BY id ASC")
    fun getPemilik(): List<Pemilik>

    @Query("SELECT * FROM pemilik WHERE id=:pemilik_id")
    suspend fun getPemilikId(pemilik_id: String): List<Pemilik>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pemilik: Pemilik)

    @Update
    fun updatePemilik(pemilik: Pemilik)

    @Delete
    fun deletePemilik(pemilik: Pemilik)

}