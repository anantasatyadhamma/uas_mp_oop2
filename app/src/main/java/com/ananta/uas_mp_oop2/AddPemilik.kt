package com.ananta.uas_mp_oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ananta.uas_mp_oop2.database.AdopsiDB
import com.ananta.uas_mp_oop2.database.Constant
import com.ananta.uas_mp_oop2.database.Hewan
import com.ananta.uas_mp_oop2.database.Pemilik
import kotlinx.android.synthetic.main.activity_add_hewan_.*
import kotlinx.android.synthetic.main.activity_add_pemilik.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPemilik : AppCompatActivity() {
    val db by lazy { AdopsiDB.getDatabase(this)}
    private var pemilikId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pemilik)
        addPemilik()
        setupView()
    }
    fun setupView(){
        val intentType = intent.getIntExtra("intent_type", 0)
        when(intentType){
            Constant.TYPE_CREATE -> {
                btn_update_pemilik.visibility = View.GONE
            }
            Constant.TYPE_UPDATE -> {
                btn_create_pemilik.visibility = View.GONE
                getPemilik()
            }
        }
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
        btn_update_pemilik.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.pemilikDao().updatePemilik(
                    Pemilik(pemilikId, edtNamePemilik.text.toString(), edtUmurPemilik.text.toString(), edtTelp.text.toString(), edtAlamat.text.toString(), edtHewan.text.toString())

                )
                finish()
            }
        }
    }
    fun getPemilik(){
        pemilikId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.pemilikDao().getPemilikId(pemilikId)[0]
            edtNamePemilik.setText(data.nama_pemilik)
            edtUmurPemilik.setText(data.umur)
            edtTelp.setText(data.no_hp)
            edtAlamat.setText(data.alamat)
            edtHewan.setText(data.hewan_adopsi)
        }
    }
}
