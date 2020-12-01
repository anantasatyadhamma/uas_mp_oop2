package com.ananta.uas_mp_oop2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pemilik")
data class Pemilik (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nama_pemilik") val nama_pemilik: String,
    @ColumnInfo(name = "umur") val umur: Int,
    @ColumnInfo(name = "no_hp") val no_hp: Int,
    @ColumnInfo(name = "alamat") val alamat: String,
    @ColumnInfo(name = "hewan_adopsi") val hewan_adopsi: Int,
)