package com.ananta.uas_mp_oop2.database

import androidx.room.*


@Dao
interface HewanDao {
    @Query("SELECT * FROM hewan ORDER BY id ASC")
    fun getHewan(): List<Hewan>

    @Insert
    fun insert(hewan: Hewan)

    @Update
    fun updateHewan(hewan: Hewan)

    @Delete
    fun deleteHewan(hewan: Hewan)

}