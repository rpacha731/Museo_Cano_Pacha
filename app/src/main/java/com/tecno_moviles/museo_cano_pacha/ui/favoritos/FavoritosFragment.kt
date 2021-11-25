package com.tecno_moviles.museo_cano_pacha.ui.favoritos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tecno_moviles.museo_cano_pacha.R
import com.tecno_moviles.museo_cano_pacha.database.BaseDatos
import com.tecno_moviles.museo_cano_pacha.database.BaseDatos.Companion.getInstance
import com.tecno_moviles.museo_cano_pacha.resultado_qr.ResultadoActivity

class FavoritosFragment : Fragment(), RecyclerViewOnClickListener {

    private val favoritosList = mutableListOf<Favorito> ()
    lateinit var recyclerView: RecyclerView
    private lateinit var btnCorazon : View
    private lateinit var backItem : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_favoritos, container, false)

        initFavs()

        recyclerView = view.findViewById(R.id.recyclerFavoritos)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = FavoritosListAdapter(favoritosList, this)

        return view
    }

    private fun initFavs () {

        val baseDatos = getInstance(context)
        val lista = baseDatos!!.getFavs("leo")
        favoritosList.clear()
        for (i in lista.indices) {
            if (i % 2 == 0) favoritosList.add(Favorito("mona", "La Giaconda", "soy una descripción", true))
            else favoritosList.add(Favorito("dedos", "Dedos dedosos", "soy una descripción 2", true))
        }
    }

    override fun onItemClick(position: Int) {
        val baseDatos = getInstance(context)
        btnCorazon = recyclerView[position].findViewById(R.id.viewCorazon)
        btnCorazon.setOnClickListener {
            if (favoritosList[position].esFav) {
                favoritosList[position].esFav = false
                it.background = resources.getDrawable(R.drawable.ic_favorite)
                baseDatos!!.deleteFav(1, "leo")
            }
            this.onResume()
        }

        backItem = recyclerView[position].findViewById(R.id.back_item)
        backItem.setOnClickListener {
            startActivity(Intent(activity?.applicationContext, ResultadoActivity::class.java))
        }

    }


}