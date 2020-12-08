package com.ananta.uas_mp_oop2.database

import androidx.room.*


@Dao
interface HewanDao {
    @Query("SELECT * FROM hewan ORDER BY id ASC")
    suspend fun getHewan(): List<Hewan>

    @Insert
    suspend fun insert(hewan: Hewan)

    @Update
    suspend fun updateHewan(hewan: Hewan)

    @Delete
    suspend fun deleteHewan(hewan: Hewan)

}