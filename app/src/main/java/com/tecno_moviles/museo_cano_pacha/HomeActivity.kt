package com.tecno_moviles.museo_cano_pacha

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.integration.android.IntentIntegrator
import com.tecno_moviles.museo_cano_pacha.databinding.ActivityHomeBinding
import com.tecno_moviles.museo_cano_pacha.resultado_qr.ResultadoActivity
import com.tecno_moviles.museo_cano_pacha.splash.SplashActivity
import com.tecno_moviles.museo_cano_pacha.utils.RESULTADO_QR
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.text.Html
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService


class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private val PERMISOS: Array<String> = arrayOf(Manifest.permission.CAMERA,
                                                  Manifest.permission.ACCESS_NETWORK_STATE,
                                                  Manifest.permission.INTERNET)

    companion object {
        private lateinit var headerView: View
        private lateinit var context: Context

        fun setContext(con: Context) {
            context = con
        }

        fun updateDrawer () {
            headerView.findViewById<TextView>(R.id.drawerUsername).text = SplashActivity.prefs.username
            headerView.findViewById<TextView>(R.id.drawerUserMail).text = SplashActivity.prefs.userEmail
        }
        fun checkConnection () {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork : NetworkInfo? = cm.activeNetworkInfo
            if (activeNetwork?.isConnectedOrConnecting != true) {
//                val alertDialog : AlertDialog? = this.let {
//                    val builder = AlertDialog.Builder(this.context)
//                    builder.apply {
//                        setMessage("No posee conexión a internet, no podrá ver los items del museo :(")
//                        setTitle("Sin Internet")
//                        setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, _ -> dialogInterface.dismiss() })
//                    }
//                    builder.create()
//                }
//                alertDialog?.show()
                Toast.makeText(context, "No posee conexión a internet, no podrá ver los items del museo :(", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (permiso in PERMISOS) {
            if (ContextCompat.checkSelfPermission(applicationContext, permiso) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISOS, 100)
            }
        }

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
        actionBar?.title = Html.fromHtml("<font color='#ff0000'>ActionBarTitle </font>")
        setupActionBarWithNavController(navController, appBarConfiguration)

        headerView = binding.navView.getHeaderView(0)
        updateDrawer()

        setContext(applicationContext)
        checkConnection()

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }

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
                val intent = Intent(this, ResultadoActivity::class.java).apply {
                    putExtra(RESULTADO_QR, result.contents.toString())
                }
                startActivity(intent)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}