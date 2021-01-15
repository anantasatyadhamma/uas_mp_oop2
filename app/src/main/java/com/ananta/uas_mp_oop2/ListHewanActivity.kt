package com.ananta.uas_mp_oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.ananta.uas_mp_oop2.database.AdopsiDB
import com.ananta.uas_mp_oop2.database.Constant
import com.ananta.uas_mp_oop2.database.Hewan
import kotlinx.android.synthetic.main.activity_list_hewan.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListHewanActivity : AppCompatActivity() {

    val db by lazy { AdopsiDB.getDatabase(this)}
    lateinit var hewanAdapter: HewanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_hewan)
        setupRecyclerView()

    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            val hewan =  db.hewanDao().getHewan()
            Log.d("ListHewanActivity", "respons : $hewan")
            withContext(Dispatchers.Main){
                hewanAdapter.setData( hewan )
            }
        }
    }

    fun intentEdit(hewanId: String, intentType: Int){
        startActivity(
            Intent(applicationContext, AddHewan_Activity::class.java)
                .putExtra("intent_id", hewanId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun setupRecyclerView(){
        hewanAdapter = HewanAdapter(arrayListOf(), object  : HewanAdapter.onAdapterListener{
            override fun onClick(hewan: Hewan) {
                Toast.makeText(applicationContext, hewan.nama_hewan + ", " + hewan.umur + ", " + hewan.owner, Toast.LENGTH_LONG).show()
            }

            override fun onDelete(hewan: Hewan) {
                CoroutineScope(Dispatchers.IO).launch {
                    db.hewanDao().deleteHewan(hewan)
                    loadData()
                }
            }

            override fun onUpdate(hewan: Hewan) {
                intentEdit(hewan.id,Constant.TYPE_UPDATE)
            }

        })
        list_hewan.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = hewanAdapter
        }
    }
}