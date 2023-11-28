package com.thisisit.droiddexcompose.model

import retrofit2.Call
import retrofit2.http.GET

interface PokemonApiService {
    @GET("pokemon/")
    fun getPokemonList(): Call<PokemonListResponse>

//    @GET
//    fun getPokemonDetails(@Url url: String): Call<Pokemon>
}