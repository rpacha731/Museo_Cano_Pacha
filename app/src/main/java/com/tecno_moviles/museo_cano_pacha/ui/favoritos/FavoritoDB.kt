package com.tecno_moviles.museo_cano_pacha.ui.favoritos

data class FavoritoDB(
    val id_qr : Int? = null,
    val username : String? = null,
    var esFav: Boolean
)
