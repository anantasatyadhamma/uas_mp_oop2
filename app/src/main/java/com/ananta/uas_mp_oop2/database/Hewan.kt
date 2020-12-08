package com.ananta.uas_mp_oop2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hewan")
data class Hewan(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nama_hewan") val nama_hewan: String,
    @ColumnInfo(name = "jenis_kelamin") val jenis_kelamin: String,
    @ColumnInfo(name = "umur") val umur: String,
    @ColumnInfo(name = "ras") val ras: String,
    @ColumnInfo(name = "owner") val owner: String,

)