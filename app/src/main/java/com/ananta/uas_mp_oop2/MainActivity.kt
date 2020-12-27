package com.ananta.uas_mp_oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ananta.uas_mp_oop2.database.Constant
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addHewan()
        getListHewan()
        addPemilik()
        getListPemilik()
    }

     fun addHewan() {
         btn_add_hewan.setOnClickListener{
             intentEdit(0, Constant.TYPE_CREATE)
         }
     }

    fun intentEdit(hewanId: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, AddHewan_Activity::class.java)
                .putExtra("intent_id", hewanId)
                .putExtra("intent_type", intentType)
        )
    }

    fun getListHewan(){
        btn_list_hewan.setOnClickListener{
            startActivity(Intent(this, ListHewanActivity::class.java))
        }
    }

    fun addPemilik() {
        btn_add_pemilik.setOnClickListener{
            startActivity(Intent(this, AddPemilik::class.java))
        }
    }

    fun getListPemilik(){
        btn_list_pemilik.setOnClickListener{
            startActivity(Intent(this, ListPemilik::class.java))
        }
    }
}