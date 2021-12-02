package com.tecno_moviles.museo_cano_pacha.services

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tecno_moviles.museo_cano_pacha.ui.listado.Item
import com.tecno_moviles.museo_cano_pacha.ui.listado.ListadoFragment
import org.json.JSONArray
import org.json.JSONObject

class NetworkService () {

    companion object {
        lateinit var result: ItemMuseo
        var listItems = mutableListOf<Item>()
        var volvi : Boolean = false
    }

    val listener = object : JSONObjectRequestListener {
        override fun onResponse(response: JSONObject?) {

            // NO PUEDO INSTANCIAR DIRECTAMENTE EL OBJETO PORQUE NO SON
            // LOS MISMOS JSONOBJECT QUE DEVUELVE LA LLAMADA A LA FUNCIÓN
            // DE NETWORKING

            var listGallery = mutableListOf<ItemGallery>()
            var aux = response?.getJSONArray("item_gallery")
            for (i in 0 until aux!!.length()) {
                var it = aux.getJSONObject(i)
                listGallery.add(ItemGallery(it.getString("url"), it.getString("description")))
            }

            var listExternalLinks = mutableListOf<ExternalLinks>()
            var aux2 = response?.getJSONArray("item_external_links")
            for (i in 0 until aux2!!.length()) {
                var it = aux2.getJSONObject(i)
                listExternalLinks.add(ExternalLinks(it.getString("url"), it.getString("name")))
            }

            result = ItemMuseo(
                response?.getString("id")!!,
                response?.getString("item_title"),
                response?.getString("room_name"),
                response?.getString("item_intro"),
                response?.getString("item_main_content"),
                response?.getString("item_main_picture"),
                listGallery,
                response?.getString("item_youtube"),
                response?.getString("item_tags"),
                listExternalLinks,
                response?.getString("item_lat"),
                response?.getString("item_long"),
                response?.getString("item_audio_link")
            )

            volvi = true
        }

        override fun onError(anError: ANError?) {
            Log.println(Log.ERROR, "ERROR", anError.toString())
        }
    }

    val listener2 = object : JSONArrayRequestListener {
        override fun onResponse(response: JSONArray?) {

            var list : JSONArray? = response

            for (aux : JSONObject in list?.toMutableList()!!) {
                listItems.add(
                    Item(Integer.parseInt(aux.get("id").toString()),
                    aux.get("title").toString(),
                    aux.get("roomName").toString())
                )
            }
            volvi = true
        }

        override fun onError(anError: ANError?) {
            Log.println(Log.ERROR, "ERROR", anError.toString())
        }
    }

    fun JSONArray.toMutableList(): MutableList<JSONObject> = MutableList(length(), this::getJSONObject)

    fun initialize(context: Context) {
        AndroidNetworking.initialize(context)
    }

    fun getItemMuseoByQR (qr : String) {
        // CAMBIAR LA URL POR LA IP O DOMINIO CORRESPONDIENTE
        AndroidNetworking.get("http://192.168.1.4:8080/api/item-museo?qr=$qr")
            .build().getAsJSONObject(listener)
    }

    fun getListOfItemsMuseo () {
        AndroidNetworking.get("http://192.168.1.4:8080/api/items-museo")
            .build().getAsJSONArray(listener2)
    }

}