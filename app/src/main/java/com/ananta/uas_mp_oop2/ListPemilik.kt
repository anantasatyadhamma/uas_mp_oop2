package com.ananta.uas_mp_oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ananta.uas_mp_oop2.database.AdopsiDB
import com.ananta.uas_mp_oop2.database.Constant
import com.ananta.uas_mp_oop2.database.Pemilik
import kotlinx.android.synthetic.main.activity_list_pemilik.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListPemilik : AppCompatActivity() {
    val db by lazy { AdopsiDB.getDatabase(this)}
    lateinit var pemilikAdapter: PemilikAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pemilik)
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }
    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val pemilik = db.pemilikDao().getPemilik()
            Log.d("ListPemilikActivity", "data pemilik : $pemilik")
            withContext(Dispatchers.Main) {
                pemilikAdapter.setData(pemilik)
            }
        }
    }
    fun intentEdit(pemilikId: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, AddPemilik::class.java)
                .putExtra("intent_id", pemilikId)
                .putExtra("intent_type", intentType)
        )
    }
    private fun setupRecyclerView(){
        pemilikAdapter = PemilikAdapter(arrayListOf(), object  : PemilikAdapter.onAdapterListener{
            override fun onClick(pemilik: Pemilik) {
                Toast.makeText(applicationContext, pemilik.nama_pemilik + ", " + pemilik.umur + ", " + pemilik.alamat, Toast.LENGTH_LONG).show()
            }

            override fun onDelete(pemilik: Pemilik) {
                CoroutineScope(Dispatchers.IO).launch {
                    db.pemilikDao().deletePemilik(pemilik)
                    loadData()
                }
            }
            override fun onUpdate(pemilik: Pemilik) {
                intentEdit(pemilik.id, Constant.TYPE_UPDATE)
            }
        })
        list_pemilik.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = pemilikAdapter
        }
    }
}