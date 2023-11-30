package com.thisisit.droiddexcompose.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val name: String,
    val url: String
)

data class PokemonDetails(
    val name: String,
    val height: Int,
    val id: Int,
    val sprites: Sprites,
    val weight: Int
)

data class Sprites(
    val other: OtherSprites
)

data class OtherSprites(
    @SerializedName("official-artwork")
    val official_artwork: PokemonImage
)

data class PokemonImage(
    val front_default: String
)

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)
