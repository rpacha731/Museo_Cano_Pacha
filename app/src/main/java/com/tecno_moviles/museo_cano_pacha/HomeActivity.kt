package com.tecno_moviles.museo_cano_pacha

import android.content.Intent
import android.os.Bundle
import android.view.Menu
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
import com.google.zxing.integration.android.IntentIntegrator
import com.tecno_moviles.museo_cano_pacha.databinding.ActivityHomeBinding
import com.tecno_moviles.museo_cano_pacha.databinding.FragmentHomeBinding
import com.tecno_moviles.museo_cano_pacha.ui.home.HomeFragment

class HomeActivity : AppCompatActivity() {

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
                R.id.nav_ayuda,
                R.id.nav_cerrar_sesion
            ), drawerLayout
        )

        navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)


        var b : Button? = findViewById(R.id.btn_escanear)

        b?.setOnClickListener {
            IntentIntegrator(this).initiateScan()
        }



        println(b.toString() +  "***************************")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
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