package com.thisisit.droiddexcompose.model

data class Pokemon(
    val name: String,
    val url: String
)

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)
