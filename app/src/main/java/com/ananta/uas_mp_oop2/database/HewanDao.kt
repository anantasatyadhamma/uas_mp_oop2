package com.ananta.uas_mp_oop2.database

import androidx.room.*


@Dao
interface HewanDao {
    @Query("SELECT * FROM hewan ORDER BY id ASC")
    suspend fun getHewan(): List<Hewan>

    @Query("SELECT * FROM hewan WHERE id=:hewan_id")
    suspend fun getHewanId(hewan_id: String): List<Hewan>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hewan: Hewan)

    @Update
    suspend fun updateHewan(hewan: Hewan)

    @Delete
    suspend fun deleteHewan(hewan: Hewan)


}