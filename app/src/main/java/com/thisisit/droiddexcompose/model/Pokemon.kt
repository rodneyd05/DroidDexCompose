package com.thisisit.droiddexcompose.model

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Add id 'kotlin-parcelize' in plugin to import kotlinx.parcelize.Parcelize successfully
data class Pokemon(
    val name: String,
    val url: String
)

@Parcelize
data class PokemonDetails(
    val name: String,
    val height: Int,
    val id: Int,
    val sprites: Sprites,
    val weight: Int
): Parcelable

@Parcelize
data class Sprites(
    val other: OtherSprites
): Parcelable

@Parcelize
data class OtherSprites(
    @SerializedName("official-artwork")
    val official_artwork: PokemonImage
): Parcelable

@Parcelize
data class PokemonImage(
    val front_default: String
): Parcelable

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)
