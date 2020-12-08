package com.ananta.uas_mp_oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ananta.uas_mp_oop2.database.AdopsiDB
import com.ananta.uas_mp_oop2.database.Hewan
import com.ananta.uas_mp_oop2.database.Pemilik
import kotlinx.android.synthetic.main.activity_add_hewan_.*
import kotlinx.android.synthetic.main.activity_add_pemilik.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPemilik : AppCompatActivity() {
    val db by lazy { AdopsiDB.getDatabase(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pemilik)
        addPemilik()
    }

    fun addPemilik(){
        btn_create_pemilik.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.pemilikDao().insert(
                    Pemilik(0, edtNamePemilik.text.toString(), edtUmurPemilik.text.toString(), edtTelp.text.toString(), edtAlamat.text.toString(), edtHewan.text.toString())
                )
                finish()
            }
        }
    }
}