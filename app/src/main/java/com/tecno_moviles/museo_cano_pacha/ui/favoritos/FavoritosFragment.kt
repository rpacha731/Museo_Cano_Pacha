package com.tecno_moviles.museo_cano_pacha.ui.favoritos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.tecno_moviles.museo_cano_pacha.R
import com.tecno_moviles.museo_cano_pacha.database.BaseDatos.Companion.getInstance
import com.tecno_moviles.museo_cano_pacha.resultado_qr.ResultadoActivity
import com.tecno_moviles.museo_cano_pacha.splash.SplashActivity
import com.tecno_moviles.museo_cano_pacha.ui.listado.Item
import com.tecno_moviles.museo_cano_pacha.utils.RESULTADO_QR
import org.json.JSONArray
import org.json.JSONObject

class FavoritosFragment : Fragment(), RecyclerViewOnClickListener {

    private val favoritosList = mutableListOf<Item>()
    lateinit var recyclerView: RecyclerView
    private lateinit var btnCorazon: View
    private lateinit var backItem: View

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

    private fun initFavs() {

        AndroidNetworking.initialize(context)

        val baseDatos = getInstance(context)

        val listener2 = object : JSONArrayRequestListener {
            override fun onResponse(response: JSONArray?) {
                favoritosList.clear()
                for (aux: JSONObject in response?.toMutableList()!!) {
                    if (baseDatos!!.isFav(
                            SplashActivity.prefs.username.toString(),
                            aux.get("id").toString()
                        )
                    ) {
                        favoritosList.add(
                            Item(
                                Integer.parseInt(aux.get("id").toString()),
                                aux.get("title").toString(),
                                aux.get("roomName").toString(),
                                aux.get("itemMainPicture").toString()
                            )
                        )
                    }
                }
                recyclerView.adapter = FavoritosListAdapter(favoritosList, this@FavoritosFragment)
            }

            override fun onError(anError: ANError?) {
                Log.println(Log.ERROR, "ERROR", anError.toString())
            }
        }

        AndroidNetworking.get("http://192.168.1.9:8080/api/items-museo")
            .build().getAsJSONArray(listener2)

    }

    fun JSONArray.toMutableList(): MutableList<JSONObject> =
        MutableList(length(), this::getJSONObject)

    override fun onItemClick(position: Int) {
        val baseDatos = getInstance(context)
        btnCorazon = recyclerView[position].findViewById(R.id.viewCorazon)
        btnCorazon.setOnClickListener {
            it.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.ic_favorite,
                requireActivity().theme
            )
            baseDatos!!.deleteFav(
                favoritosList[position].id!!,
                SplashActivity.prefs.username.toString()
            )
            Toast.makeText(
                context,
                "Vuelva atrás para actualizar los favoritos :)",
                Toast.LENGTH_LONG
            ).show()
        }

        backItem = recyclerView[position].findViewById(R.id.back_item)
        backItem.setOnClickListener {
            val intent = Intent(requireActivity(), ResultadoActivity::class.java).apply {
                putExtra(RESULTADO_QR, favoritosList[position].id.toString())
            }
            startActivity(intent)
        }

    }


}