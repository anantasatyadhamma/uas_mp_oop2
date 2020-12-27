package com.ananta.uas_mp_oop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ananta.uas_mp_oop2.database.Hewan
import kotlinx.android.synthetic.main.adapter_hewan.view.*

class HewanAdapter (private val hewan: ArrayList<Hewan>, private val listener: onAdapterListener) :
    RecyclerView.Adapter<HewanAdapter.HewanViewHolder>() {

    class HewanViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Hewan>){
        hewan.clear()
        hewan.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HewanViewHolder {
        return HewanViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_hewan, parent,false)
        )
    }

    override fun onBindViewHolder(holder: HewanViewHolder, position: Int) {
        val h = hewan[position]
        holder.view.text_nama.text = h.nama_hewan
        holder.view.text_nama.setOnClickListener {
            listener.onClick(h)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(h)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(h)
        }

    }

    override fun getItemCount() = hewan.size

    interface onAdapterListener{
        fun onClick(hewan: Hewan)
        fun onDelete(hewan: Hewan)
        fun onUpdate(hewan: Hewan)
    }

}