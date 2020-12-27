package com.ananta.uas_mp_oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ananta.uas_mp_oop2.database.AdopsiDB
import com.ananta.uas_mp_oop2.database.Constant
import com.ananta.uas_mp_oop2.database.Hewan
import kotlinx.android.synthetic.main.activity_add_hewan_.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddHewan_Activity : AppCompatActivity() {

    val db by lazy {AdopsiDB.getDatabase(this)}
    private var hewanId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hewan_)
        addHewan()
        setupView()


    }

    fun setupView(){
        val intentType = intent.getIntExtra("intent_type", 0)
        when(intentType){
            Constant.TYPE_CREATE -> {
                btn_update_hewan.visibility = View.GONE
            }
            Constant.TYPE_UPDATE -> {
                btn_create_hewan.visibility = View.GONE
                getHewan()
            }
        }
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
        btn_update_hewan.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.hewanDao().updateHewan(
                    Hewan(hewanId, edtNameHewan.text.toString(), edtJK.text.toString(), edtUmurHewan.text.toString(), edtRas.text.toString(), edtOwner.text.toString())

                )
                finish()
            }
        }
    }

    fun getHewan(){
        hewanId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.hewanDao().getHewanId(hewanId)[0]
            edtNameHewan.setText(data.nama_hewan)
            edtUmurHewan.setText(data.umur)
            edtOwner.setText(data.owner)
            edtRas.setText(data.ras)
            edtJK.setText(data.jenis_kelamin)
        }
    }

}