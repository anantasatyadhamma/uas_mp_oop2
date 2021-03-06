package com.ananta.uas_mp_oop2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pemilik")
data class Pemilik (
    @PrimaryKey val id: String,
    @ColumnInfo(name = "nama_pemilik") val nama_pemilik: String,
    @ColumnInfo(name = "umur") val umur: String,
    @ColumnInfo(name = "no_hp") val no_hp: String,
    @ColumnInfo(name = "alamat") val alamat: String,
    @ColumnInfo(name = "hewan_adopsi") val hewan_adopsi: String,
)