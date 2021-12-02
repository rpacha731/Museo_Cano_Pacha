package com.tecno_moviles.museo_cano_pacha.ui.listado

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.tecno_moviles.museo_cano_pacha.R
import com.tecno_moviles.museo_cano_pacha.resultado_qr.ResultadoActivity
import com.tecno_moviles.museo_cano_pacha.services.NetworkService
import com.tecno_moviles.museo_cano_pacha.splash.SplashActivity
import org.json.JSONArray
import org.json.JSONObject

class ListadoFragment : Fragment(), RecyclerViewOnClickListener {

    companion object {
        var listadoList = mutableListOf<Item>()
    }

    lateinit var recyclerView: RecyclerView
    private lateinit var flecha : View
    private lateinit var progress : LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return initListado(inflater, container)
    }

    private fun initListado(inflater: LayoutInflater,
                             container: ViewGroup?) : View {

        val view = inflater.inflate(R.layout.fragment_listado, container, false)
        recyclerView = view.findViewById(R.id.recyclerListado)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        SplashActivity.networking.getListOfItemsMuseo()

        listadoList.clear()

        listadoList = NetworkService.listItems

        println(listadoList)

        progress = view.findViewById(R.id.progressBar)

        progress.visibility = View.INVISIBLE

        recyclerView.adapter = ListadoListAdapter(listadoList, this@ListadoFragment)

//        AndroidNetworking.initialize(context)
//        val listener = object : JSONObjectRequestListener {
//            override fun onResponse(response: JSONObject?) {
//
//                var list : JSONArray? = response?.getJSONArray("items_list")
//                listadoList.clear()
//
//                for (aux : JSONObject in list?.toMutableList()!!) {
//                    listadoList.add(Item(Integer.parseInt(aux.get("id").toString()),
//                        aux.get("title").toString(),
//                        aux.get("room_name").toString()))
//                }
//
//                progress.visibility = View.INVISIBLE
//
//                recyclerView.adapter = ListadoListAdapter(listadoList, this@ListadoFragment)
//            }
//            override fun onError(anError: ANError?) {
//                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
//            }
//        }
//
//        AndroidNetworking.get("https://mocki.io/v1/153d425d-28d3-42f9-9bfa-568dd085ad14")
//            .build().getAsJSONObject(listener)

        return view
    }

//    fun JSONArray.toMutableList(): MutableList<JSONObject> = MutableList(length(), this::getJSONObject)

    override fun onItemClick(position: Int) {
        flecha = recyclerView[position].findViewById(R.id.viewFlecha)
        flecha.setOnClickListener {
            startActivity(Intent(this.activity, ResultadoActivity::class.java))
        }
    }


}