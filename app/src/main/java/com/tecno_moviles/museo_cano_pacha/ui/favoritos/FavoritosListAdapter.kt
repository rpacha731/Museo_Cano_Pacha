package com.tecno_moviles.museo_cano_pacha.ui.favoritos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.widget.ANImageView
import com.tecno_moviles.museo_cano_pacha.R
import com.tecno_moviles.museo_cano_pacha.ui.listado.Item

class FavoritosListAdapter (private val data: List<Item>, private val listener: RecyclerViewOnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder> () {

    lateinit var aux : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        aux = parent.context
        val row = LayoutInflater.from(parent.context).inflate(R.layout.item_view_favorito, parent, false)
        return FavoritosListViewHolder(row, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val favo = data.get(position)

        (holder as FavoritosListViewHolder).imgFav.setImageUrl(favo.itemMainPicture)
        holder.titleFav.text = favo.title
        holder.roomName.text = favo.roomName
        holder.corazon.background = AppCompatResources.getDrawable(aux, R.drawable.ic_favorite_red)
    }

    override fun getItemCount(): Int = data.size

}

class FavoritosListViewHolder (itemView: View, listener: RecyclerViewOnClickListener) : RecyclerView.ViewHolder(itemView) {
    var imgFav : ANImageView = itemView.findViewById(R.id.imgFav)
    var titleFav : TextView = itemView.findViewById(R.id.titleFav)
    var roomName : TextView = itemView.findViewById(R.id.roomNamee)
    var corazon : View = itemView.findViewById(R.id.viewCorazon)

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}

interface RecyclerViewOnClickListener {
    fun onItemClick (position: Int)
}