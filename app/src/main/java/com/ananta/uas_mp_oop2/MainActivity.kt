package com.ananta.uas_mp_oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addHewan()
        getListHewan()
    }

     fun addHewan() {
         btn_add_hewan.setOnClickListener{
             startActivity(Intent(this, AddHewan_Activity::class.java))
         }
     }

    fun getListHewan(){
        btn_list_hewan.setOnClickListener{
            startActivity(Intent(this, ListHewanActivity::class.java))
        }
    }


}