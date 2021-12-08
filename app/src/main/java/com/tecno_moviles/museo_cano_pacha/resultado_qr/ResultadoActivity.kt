package com.tecno_moviles.museo_cano_pacha.resultado_qr

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.tecno_moviles.museo_cano_pacha.R
import com.tecno_moviles.museo_cano_pacha.database.BaseDatos.Companion.getInstance
import com.tecno_moviles.museo_cano_pacha.databinding.ActivityResultadoBinding
import com.tecno_moviles.museo_cano_pacha.dto.ExternalLinks
import com.tecno_moviles.museo_cano_pacha.dto.ItemMuseo
import com.tecno_moviles.museo_cano_pacha.splash.SplashActivity
import com.tecno_moviles.museo_cano_pacha.utils.RESULTADO_QR
import org.json.JSONObject
import android.widget.ArrayAdapter
import java.util.stream.Collectors
import android.widget.AdapterView
import android.widget.Toast
import com.tecno_moviles.museo_cano_pacha.utils.IP

class ResultadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoBinding
    private var qr : Int = 0
    private lateinit var itemMuseo : ItemMuseo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        qr = Integer.parseInt(intent.extras?.getString(RESULTADO_QR)!!)

        AndroidNetworking.initialize(applicationContext)

        val listener = object : JSONObjectRequestListener {
            override fun onResponse(response: JSONObject?) {

//                var listGallery = mutableListOf<ItemGallery>()
//                var aux = response?.getJSONArray("itemGallery")
//                for (i in 0 until aux!!.length()) {
//                    var it = aux.getJSONObject(i)
//                    listGallery.add(ItemGallery(it.getString("url"), it.getString("description")))
//                }

                val listExternalLinks = mutableListOf<ExternalLinks>()
                val aux2 = response?.getJSONArray("itemExternalLinks")
                for (i in 0 until aux2!!.length()) {
                    val it = aux2.getJSONObject(i)
                    listExternalLinks.add(ExternalLinks(it.getString("url"), it.getString("name")))
                }

                itemMuseo = ItemMuseo(
                    response.getString("id"),
                    response.getString("itemTitle"),
                    response.getString("roomName"),
                    response.getString("itemIntro"),
                    response.getString("itemMainContent"),
                    response.getString("itemMainPicture"),
                    mutableListOf(),  //listGallery,     // Por ahora no la usamos
                    response.getString("itemYoutube"),
                    response.getString("itemTags"),
                    listExternalLinks,
                    response.getString("itemLat"),
                    response.getString("itemLong"),
                    response.getString("itemAudioLink")
                )

                binding.textTitulo.text = itemMuseo.itemTitle

                binding.sala.text = itemMuseo.roomName

                binding.intro.text = itemMuseo.itemIntro

                binding.mainContent.text = itemMuseo.itemMainContent

                binding.imgResultado.setImageUrl(itemMuseo.itemMainPicture)

                binding.youtube.text = Html.fromHtml("<u>Link</u>", Html.FROM_HTML_MODE_LEGACY)

                binding.tags.text = itemMuseo.itemTags

                val listaLinks = listExternalLinks.stream().map { t -> t.name }.collect(Collectors.toList())
                listaLinks.add(0, "Links")

                val adapter: ArrayAdapter<String> = ArrayAdapter<String>( applicationContext, android.R.layout.simple_spinner_item, listaLinks )

                binding.spinnerExternalLinks.adapter = adapter
                binding.spinnerExternalLinks.onItemSelectedListener = ( object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        if (position == 0) { return }
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(listExternalLinks[position - 1].url)))
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) { }
                })

                binding.latitud.text = itemMuseo.itemLat

                binding.longitud.text = itemMuseo.itemLong

                val baseDatos = getInstance(this@ResultadoActivity)
                val isFav = baseDatos!!.isFav(SplashActivity.prefs.username.toString(), itemMuseo.id)

                if (isFav) {
                    binding.floatingActionButton.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_red, theme)
                } else {
                    binding.floatingActionButton.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite, theme)
                }

                binding.progressBar2.visibility = View.INVISIBLE

            }
            override fun onError(anError: ANError?) {
                Log.println(Log.ERROR, "ERROR", anError!!.toString())
                if (anError.response.code() == 404) {
                    Toast.makeText(applicationContext, "No existe un item de museo con ese QR", Toast.LENGTH_LONG).show()
                    finish()
                }

            }
        }

        AndroidNetworking.get("http://$IP:8080/api/item-museo?qr=$qr")
            .build().getAsJSONObject(listener)


        binding.youtube.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(itemMuseo.itemYoutube)))
        }

        val baseDatos = getInstance(this)

        binding.floatingActionButton.setOnClickListener {
            val isFav = baseDatos!!.isFav(SplashActivity.prefs.username.toString(), itemMuseo.id)
            if (!isFav) {
                baseDatos.addFav(Integer.parseInt(itemMuseo.id), SplashActivity.prefs.username.toString())
                it.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite_red, theme)
            } else {
                baseDatos.deleteFav(Integer.parseInt(itemMuseo.id), SplashActivity.prefs.username.toString())
                it.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_favorite, theme)
            }

        }
    }
}