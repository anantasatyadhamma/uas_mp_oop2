package com.ananta.uas_mp_oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ananta.uas_mp_oop2.database.AdopsiDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListPemilik : AppCompatActivity() {
    val db by lazy { AdopsiDB.getDatabase(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pemilik)
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val pemilik =  db.pemilikDao().getPemilik()
            Log.d("ListPemilikActivity", "data pemilik : $pemilik")
        }
    }
}