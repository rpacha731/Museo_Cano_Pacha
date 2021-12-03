package com.tecno_moviles.museo_cano_pacha.ui.listado

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.widget.ANImageView
import com.tecno_moviles.museo_cano_pacha.R
import de.hdodenhof.circleimageview.CircleImageView

class ListadoListAdapter (private val data: List<Item>, private val listener: RecyclerViewOnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder> () {

    lateinit var aux : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.item_view_listado, parent, false)
        aux = parent.context
        return ListadoListViewHolder(row, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        (holder as ListadoListViewHolder).title.text = item.title
        holder.roomName.text = item.roomName
        holder.itemMainPicture.setImageUrl(item.itemMainPicture)
    }

    override fun getItemCount(): Int = data.size

}

class ListadoListViewHolder (itemView: View, listener: RecyclerViewOnClickListener) : RecyclerView.ViewHolder(itemView) {
    var title : TextView = itemView.findViewById(R.id.tituloLista)
    var roomName : TextView = itemView.findViewById(R.id.roomName)
    var itemMainPicture : ANImageView = itemView.findViewById(R.id.imgItemList)

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}

interface RecyclerViewOnClickListener {
    fun onItemClick (position: Int)
}