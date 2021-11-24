package com.tecno_moviles.museo_cano_pacha.ui.listado

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tecno_moviles.museo_cano_pacha.R

class ListadoListAdapter (private val data: List<Item>, private val listener: RecyclerViewOnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder> () {

    lateinit var aux : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.item_view_listado, parent, false)
        aux = parent.context
        return ListadoListViewHolder(row, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        (holder as ListadoListViewHolder).tituloListado.text = item.title
        holder.roomName.text = item.room_mame
    }

    override fun getItemCount(): Int = data.size

}

class ListadoListViewHolder (itemView: View, listener: RecyclerViewOnClickListener) : RecyclerView.ViewHolder(itemView) {
    var tituloListado : TextView = itemView.findViewById(R.id.tituloLista)
    var roomName : TextView = itemView.findViewById(R.id.roomName)

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}

interface RecyclerViewOnClickListener {
    fun onItemClick (position: Int)
}