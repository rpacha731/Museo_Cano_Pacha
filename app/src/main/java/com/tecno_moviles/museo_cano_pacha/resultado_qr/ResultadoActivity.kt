package com.tecno_moviles.museo_cano_pacha.resultado_qr

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.BitmapRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.tecno_moviles.museo_cano_pacha.database.BaseDatos.Companion.getInstance
import com.tecno_moviles.museo_cano_pacha.databinding.ActivityResultadoBinding
import com.tecno_moviles.museo_cano_pacha.ui.listado.Item
import com.tecno_moviles.museo_cano_pacha.ui.listado.ListadoFragment
import com.tecno_moviles.museo_cano_pacha.ui.listado.ListadoListAdapter
import org.json.JSONArray
import org.json.JSONObject

class ResultadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBinding
    private var auxLink = ""
    private var auxLinkYoutube = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AndroidNetworking.initialize(applicationContext)

        val listener = object : JSONObjectRequestListener {
            override fun onResponse(response: JSONObject?) {

                binding.textTitulo.text = response?.getString("item_title")

                binding.imgResultado.setImageUrl(response?.getString("item_main_picture"))
                binding.sala.text = response?.getString("room_name")
                binding.intro.text = response?.getString("item_intro")
                binding.youtube.movementMethod = LinkMovementMethod.getInstance()
                auxLinkYoutube = response?.getString("item_youtube").toString()
                binding.youtube.text = Html.fromHtml("<u> Link </u>")
                binding.tags.text = response?.getString("item_tags")
                auxLink = response?.getJSONObject("item_external_links")?.getString("url").toString()
                var tmp = response?.getJSONObject("item_external_links")?.getString("name").toString()
                binding.link.text = Html.fromHtml("<u> $tmp </u>")

                binding.latitud.text = response?.getString("item_lat")
                binding.longitud.text = response?.getString("item_long")

                binding.progressBar2.visibility = View.INVISIBLE

            }
            override fun onError(anError: ANError?) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }
        }

        AndroidNetworking.get("https://mocki.io/v1/b80b82b4-f092-4719-a891-f20b62ec4589")
            .build().getAsJSONObject(listener)


        binding.link.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(auxLink)))
        }

        binding.youtube.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(auxLinkYoutube)))
        }

        val baseDatos = getInstance(this)

        binding.floatingActionButton.setOnClickListener {
            baseDatos!!.addFav(1, "leo")
        }
    }
}