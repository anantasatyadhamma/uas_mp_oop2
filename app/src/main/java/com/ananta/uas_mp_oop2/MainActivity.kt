package com.ananta.uas_mp_oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ananta.uas_mp_oop2.database.AdopsiDB
import com.ananta.uas_mp_oop2.database.Constant
import com.ananta.uas_mp_oop2.database.Hewan
import com.ananta.uas_mp_oop2.database.Pemilik
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_hewan_.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { AdopsiDB.getDatabase(this)}
    val database = Firebase.database
    lateinit var hewanAdapter: HewanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addHewan()
        getListHewan()
        addPemilik()
        getListPemilik()
        sync_hewan()
        sync_pemilik()
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
            intentEditPemilik(0, Constant.TYPE_CREATE)
        }
    }

    fun intentEditPemilik(pemilikId: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, AddPemilik::class.java)
                .putExtra("intent_id", pemilikId)
                .putExtra("intent_type", intentType)
        )
    }

    fun getListPemilik(){
        btn_list_pemilik.setOnClickListener{
            startActivity(Intent(this, ListPemilik::class.java))
        }
    }

    fun sync_hewan(){
        btnSync1.setOnClickListener {
            getDataFromFirebase()
            addDataToFirebase()
        }
    }

    private fun getDataFromFirebase(){

        val refHewan = database.getReference("hewan")

        refHewan.addListenerForSingleValueEvent(object : ValueEventListener {
            var uid = ""
            var nama_hewan = ""
            var jenis_kelamin = ""
            var umur = ""
            var ras = ""
            var owner = ""

            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    uid = ds.child("id").getValue(String::class.java).toString()
                    nama_hewan = ds.child("nama_hewan").getValue(String::class.java).toString()
                    jenis_kelamin = ds.child("jenis_kelamin").getValue(String::class.java).toString()
                    umur = ds.child("umur").getValue(String::class.java).toString()
                    ras = ds.child("ras").getValue(String::class.java).toString()
                    owner = ds.child("owner").getValue(String::class.java).toString()


                    CoroutineScope(Dispatchers.IO).launch {
                        if (snapshot != null) {

                            db.hewanDao().insert(Hewan(uid, nama_hewan, jenis_kelamin, umur, ras, owner))

                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
                Log.d("errornya", error.toString())
            }
        })
    }

    private fun addDataToFirebase(){
        CoroutineScope(Dispatchers.IO).launch {
            val hewan =  db.hewanDao().getHewan()
            Log.d("ListHewanMain", "respons : $hewan")
            withContext(Dispatchers.Main){
                val refHewan = database.getReference("hewan")
                refHewan.setValue(hewan)
            }
        }

    }
    fun sync_pemilik(){
        btnSync2.setOnClickListener {
            getDataPemilikFromFirebase()
            addDataPemilikToFirebase()
        }
    }
    private fun getDataPemilikFromFirebase(){
        val refPemilik = database.getReference("pemilik")

        refPemilik.addListenerForSingleValueEvent(object : ValueEventListener {
            var uid = ""
            var nama_pemilik = ""
            var umur = ""
            var no_hp = ""
            var alamat = ""
            var hewan_adopsi = ""

            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    uid = ds.child("id").getValue(String::class.java).toString()
                    nama_pemilik = ds.child("nama_pemilik").getValue(String::class.java).toString()
                    umur = ds.child("umur").getValue(String::class.java).toString()
                    no_hp = ds.child("no_hp").getValue(String::class.java).toString()
                    alamat = ds.child("alamat").getValue(String::class.java).toString()
                    hewan_adopsi = ds.child("hewan_adopsi").getValue(String::class.java).toString()


                    CoroutineScope(Dispatchers.IO).launch {
                        if (snapshot != null) {

                            db.pemilikDao().insert(Pemilik(uid, nama_pemilik, umur, no_hp, alamat, hewan_adopsi))

                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
                Log.d("errornya", error.toString())
            }
        })
    }
    private fun addDataPemilikToFirebase(){
        Log.d("pass2", "pass2")
        CoroutineScope(Dispatchers.IO).launch {
            val pemilik =  db.pemilikDao().getPemilik()
            Log.d("ListPemilikMain", "respons : $pemilik")
            withContext(Dispatchers.Main){
                val refPemilik = database.getReference("pemilik")
                refPemilik.setValue(pemilik)
            }
        }

    }
}