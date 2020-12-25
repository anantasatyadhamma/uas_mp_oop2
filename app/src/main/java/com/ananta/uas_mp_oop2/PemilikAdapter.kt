package com.ananta.uas_mp_oop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ananta.uas_mp_oop2.database.Pemilik
import kotlinx.android.synthetic.main.adapter_hewan.view.*

class PemilikAdapter (private val pemilik: ArrayList<Pemilik>, private val listener: onAdapterListener) :
    RecyclerView.Adapter<PemilikAdapter.PemilikViewHolder>() {

        class PemilikViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        fun setData(list: List<Pemilik>){
            pemilik.clear()
            pemilik.addAll(list)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PemilikViewHolder {
            return PemilikViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.adapter_hewan, parent,false)
            )
        }

        override fun onBindViewHolder(holder: PemilikViewHolder, position: Int) {
            val h = pemilik[position]
            holder.view.text_nama.text = h.nama_pemilik
            holder.view.text_nama.setOnClickListener {
                listener.onClick(h)
            }
            holder.view.icon_delete.setOnClickListener {
                listener.onDelete(h)
            }

        }

        override fun getItemCount() = pemilik.size

        interface onAdapterListener{
            fun onClick(hewan: Pemilik)
            fun onDelete(hewan: Pemilik)
        }
}