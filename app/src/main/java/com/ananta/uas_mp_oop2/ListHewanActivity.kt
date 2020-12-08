package com.ananta.uas_mp_oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ananta.uas_mp_oop2.database.AdopsiDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListHewanActivity : AppCompatActivity() {

    val db by lazy { AdopsiDB.getDatabase(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_hewan)
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val hewan =  db.hewanDao().getHewan()
            Log.d("ListHewanActivity", "respons : $hewan")
        }
    }
}