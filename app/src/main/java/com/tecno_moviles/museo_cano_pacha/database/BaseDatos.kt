package com.tecno_moviles.museo_cano_pacha.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.tecno_moviles.museo_cano_pacha.ui.favoritos.Favorito
import com.tecno_moviles.museo_cano_pacha.ui.favoritos.FavoritoDB
import java.util.ArrayList

class BaseDatos (context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_FAVS = "CREATE TABLE $TABLE_FAVS ($KEY_USERNAME TEXT, $KEY_ID_QR INTEGER)"
        db.execSQL(CREATE_TABLE_FAVS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FAVS")
        onCreate(db)
    }

    fun addFav(idQR : Int, username : String) : Long {
        val database = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_ID_QR, idQR)
        values.put(KEY_USERNAME, username)
        return database.insert(TABLE_FAVS, null, values)
    }

    fun deleteFav(idQR : Int, username : String) {
        val database = this.writableDatabase
        val selectionArgument = arrayOf(idQR.toString(), username)
        database.delete(TABLE_FAVS, "$KEY_ID_QR = ? AND $KEY_USERNAME = ?", selectionArgument)
    }

    fun getFavs(username : String) : MutableList<FavoritoDB>{
        val database = this.readableDatabase
        val selectionArgument = arrayOf(username)
        val listaFavs: MutableList<FavoritoDB> = ArrayList()
        val pointer = database.query(TABLE_FAVS, null, "$KEY_USERNAME = ?", selectionArgument, null, null, null)
        while (pointer.moveToNext()){
            listaFavs.add(FavoritoDB(pointer.getInt(1), pointer.getString(0), true))
        }
        return listaFavs
    }

    companion object {
        private var databaseInstance: BaseDatos? = null
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Basedatos.db"

        // Nombre de tabla
        private const val TABLE_FAVS = "favs"

        // Columnas tabla favoritos
        private const val KEY_USERNAME = "username"
        private const val KEY_ID_QR = "id_qr"

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context?) : BaseDatos? {
            if (databaseInstance == null) databaseInstance = BaseDatos(context)
            return databaseInstance
        }
    }
}