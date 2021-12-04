package com.tecno_moviles.museo_cano_pacha.dto

data class ItemMuseo (
    val id: String,
    val itemTitle: String,
    val roomName: String,
    val itemIntro: String,
    val itemMainContent: String,
    val itemMainPicture: String,
    val itemGallery: List<ItemGallery>,
    val itemYoutube: String,
    val itemTags: String,
    val itemExternalLinks: List<ExternalLinks>,
    val itemLat: String,
    val itemLong: String,
    val itemAudioLink: String
)

data class ItemGallery (
    val url: String,
    val description: String
)

data class ExternalLinks (
    val url: String,
    val name: String
)

