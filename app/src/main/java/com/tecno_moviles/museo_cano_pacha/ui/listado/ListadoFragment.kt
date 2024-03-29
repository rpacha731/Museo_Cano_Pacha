package com.tecno_moviles.museo_cano_pacha.ui.listado

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.tecno_moviles.museo_cano_pacha.R
import com.tecno_moviles.museo_cano_pacha.resultado_qr.ResultadoActivity
import com.tecno_moviles.museo_cano_pacha.utils.IP

import com.tecno_moviles.museo_cano_pacha.utils.RESULTADO_QR
import org.json.JSONArray
import org.json.JSONObject

class ListadoFragment : Fragment(), RecyclerViewOnClickListener {

    var listadoList = mutableListOf<Item>()
    lateinit var recyclerView: RecyclerView
    private lateinit var flecha: View
    private lateinit var progress: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = initListado(inflater, container)
        progress = view.findViewById(R.id.progressBar)
        return view
    }

    private fun initListado(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {

        val view = inflater.inflate(R.layout.fragment_listado, container, false)
        recyclerView = view.findViewById(R.id.recyclerListado)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        AndroidNetworking.initialize(context)

        val listener2 = object : JSONArrayRequestListener {
            override fun onResponse(response: JSONArray?) {
                listadoList.clear()
                for (aux: JSONObject in response?.toMutableList()!!) {
                    listadoList.add(
                        Item(
                            Integer.parseInt(aux.get("id").toString()),
                            aux.get("title").toString(),
                            aux.get("roomName").toString(),
                            aux.get("itemMainPicture").toString()
                        )
                    )
                }
                progress.visibility = View.INVISIBLE
                recyclerView.adapter = ListadoListAdapter(listadoList, this@ListadoFragment)
            }

            override fun onError(anError: ANError?) {
                Log.println(Log.ERROR, "ERROR", anError.toString())
            }
        }

        AndroidNetworking.get("http://$IP:8080/api/items-museo")
            .build().getAsJSONArray(listener2)

        return view
    }

    fun JSONArray.toMutableList(): MutableList<JSONObject> =
        MutableList(length(), this::getJSONObject)

    override fun onItemClick(position: Int) {
        flecha = recyclerView[position].findViewById(R.id.viewFlecha)
        flecha.setOnClickListener {
            val intent = Intent(requireActivity().applicationContext, ResultadoActivity::class.java).apply {
                putExtra(RESULTADO_QR, listadoList[position].id.toString())
            }
            startActivity(intent)
        }
    }


}