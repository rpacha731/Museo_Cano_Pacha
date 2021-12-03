package com.tecno_moviles.museo_cano_pacha.dto

import com.google.gson.annotations.SerializedName

data class ItemMuseo (
    @SerializedName("id")
    val id: String,
    @SerializedName("item_title")
    val item_title: String,
    @SerializedName("room_name")
    val room_name: String,
    @SerializedName("item_intro")
    val item_intro: String,
    @SerializedName("item_main_content")
    val item_main_content: String,
    @SerializedName("item_main_picture")
    val item_main_picture: String,
    @SerializedName("item_gallery")
    val item_gallery: List<ItemGallery>,
    @SerializedName("item_youtube")
    val item_youtube: String,
    @SerializedName("item_tags")
    val item_tags: String,
    @SerializedName("item_external_links")
    val item_external_links: List<ExternalLinks>,
    @SerializedName("item_lat")
    val item_lat: String,
    @SerializedName("item_long")
    val item_long: String,
    @SerializedName("item_audio_link")
    val item_audio_link: String
) {

}

data class ItemGallery (
    @SerializedName("url")
    val url: String,
    @SerializedName("description")
    val description: String
)

data class ExternalLinks (
    @SerializedName("url")
    val url: String,
    @SerializedName("name")
    val name: String
)

