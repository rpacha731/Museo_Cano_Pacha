package com.tecno_moviles.museo_cano_pacha

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView
import androidx.core.view.MenuCompat
import com.google.zxing.integration.android.IntentIntegrator
import com.tecno_moviles.museo_cano_pacha.databinding.ActivityHomeBinding
import com.tecno_moviles.museo_cano_pacha.databinding.FragmentHomeBinding
import com.tecno_moviles.museo_cano_pacha.login.MainLoginActivity
import com.tecno_moviles.museo_cano_pacha.ui.home.HomeFragment

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_perfil,
                R.id.nav_conf,
                R.id.nav_ayuda
            ), drawerLayout
        )

        navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)




//        var b : Button? = findViewById(R.id.btn_escanear)
//
//        b?.setOnClickListener {
//            IntentIntegrator(this).initiateScan()
//        }

//        var btnCerrarSesion : MenuItem? = findViewById(R.id.nav_cerrar_sesion)
//
//        btnCerrarSesion?.setOnMenuItemClickListener() {
//
//        } .setOnMenuItemClickListener {
//
//            return true
//            println("")
//        }
//
//        println(btnCerrarSesion.toString() + "////////////////////////")

//        btnCerrarSesion. ?.setOnClickListener {
//            println("holissss***********************************")
////            finish()
//            startActivity(Intent(this, MainLoginActivity::class.java))
//        }





        val btn_mail: Button = findViewById(R.id.btn_mail)
        btn_mail.setOnClickListener {
            enviarMail()
        }

    }

    fun enviarMail() {
//        val intent = Intent(Intent.ACTION_SEND).apply {
//            //type = "*/*"
//            intent.data = Uri.parse("mailto:") // only email apps should handle this
//            putExtra(Intent.EXTRA_EMAIL, addresses)
//            putExtra(Intent.EXTRA_SUBJECT, subject)
//            putExtra(Intent.EXTRA_TEXT, msg)
//        }
        val intent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "pacha.eli.2021@gmail.com", null
            )
        )
        try {
            startActivity(intent)
            Toast.makeText(applicationContext,"Mail enviado", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception) {
            Toast.makeText ( applicationContext , e.message, Toast.LENGTH_LONG) .show ()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        println("-----------------" + item.itemId)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        MenuCompat.setGroupDividerEnabled(menu, true)
        println("tamaño" + menu.size())
        menu.getItem(menu.size() - 1).setOnMenuItemClickListener {
            println("eso tilinmnnnnnnn")
            true
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Sin resultado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "El valor escaneado es: ${result.contents}", Toast.LENGTH_LONG).show()

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}