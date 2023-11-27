package com.thisisit.droiddexcompose.model

import retrofit2.Call
import retrofit2.http.GET

interface PokemonApiService {
    @GET("pokemon/")
    fun getPokemonList(): Call<PokemonListResponse>

//    @GET
//    fun getPokemonDetails(@Url url: String): Call<Pokemon>
}

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)