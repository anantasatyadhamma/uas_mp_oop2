package com.ananta.uas_mp_oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ananta.uas_mp_oop2.database.AdopsiDB
import com.ananta.uas_mp_oop2.database.Hewan
import kotlinx.android.synthetic.main.activity_add_hewan_.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddHewan_Activity : AppCompatActivity() {

    val db by lazy {AdopsiDB.getDatabase(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hewan_)
        addHewan()
    }

    fun addHewan(){
        btn_create_hewan.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.hewanDao().insert(
                    Hewan(0, edtNameHewan.text.toString(), edtJK.text.toString(), edtUmurHewan.text.toString(), edtRas.text.toString(), edtOwner.text.toString())

                )
                finish()
            }
        }
    }

}