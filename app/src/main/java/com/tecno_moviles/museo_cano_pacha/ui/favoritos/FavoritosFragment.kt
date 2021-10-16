package com.tecno_moviles.museo_cano_pacha.ui.favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tecno_moviles.museo_cano_pacha.R

class FavoritosFragment : Fragment(), RecyclerViewOnClickListener {

    private val favoritosList = mutableListOf<Favorito> ()
    lateinit var recyclerView: RecyclerView

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initFavs () {
        // Duda: cambiar por uri o por id drawable segun lo que devuelva el servidor
        favoritosList.add(Favorito("mona", "La Giaconda", "soy una descripción"))
        favoritosList.add(Favorito("dedos", "Dedos dedosos", "soy una descripción 2"))
        favoritosList.add(Favorito("grito", "El grito", "soy una descripción 3"))
        favoritosList.add(Favorito("mona", "La Giaconda", "soy una descripción"))
        favoritosList.add(Favorito("dedos", "Dedos dedosos", "soy una descripción 2"))
        favoritosList.add(Favorito("grito", "El grito", "soy una descripción 3"))
        favoritosList.add(Favorito("mona", "La Giaconda", "soy una descripción"))
        favoritosList.add(Favorito("dedos", "Dedos dedosos", "soy una descripción 2"))
        favoritosList.add(Favorito("grito", "El grito", "soy una descripción 3"))
        favoritosList.add(Favorito("mona", "La Giaconda", "soy una descripción"))
        favoritosList.add(Favorito("dedos", "Dedos dedosos", "soy una descripción 2"))
        favoritosList.add(Favorito("grito", "El grito", "soy una descripción 3"))
        favoritosList.add(Favorito("mona", "La Giaconda", "soy una descripción"))
        favoritosList.add(Favorito("dedos", "Dedos dedosos", "soy una descripción 2"))
        favoritosList.add(Favorito("grito", "El grito", "soy una descripción 3"))


    }

    override fun onItemClick(position: Int) {
        Toast.makeText(activity?.baseContext, "El titulo seleccionado es: ${favoritosList[position].titulo}", Toast.LENGTH_SHORT).show()
    }
}