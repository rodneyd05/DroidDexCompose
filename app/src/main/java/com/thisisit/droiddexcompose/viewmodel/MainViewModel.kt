package com.thisisit.droiddexcompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.thisisit.droiddexcompose.model.Pokemon
import com.thisisit.droiddexcompose.model.PokemonDetails
import com.thisisit.droiddexcompose.model.PokemonListResponse
import com.thisisit.droiddexcompose.model.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    var globalPokemonList by mutableStateOf(emptyList<Pokemon>())
        private set

    var globalPokemonDetailsList by mutableStateOf(emptyList<PokemonDetails>())
        private set

    //this variable accepts every details and pass it in a mutableState
    private val mutablePokemonDetailsList = mutableListOf<PokemonDetails>()

    fun fetchPokemonList() {

        //avoid duplicate items
        mutablePokemonDetailsList.clear()

        val call = RetrofitClient.pokemonApiService.getPokemonList()

        call.enqueue(object : Callback<PokemonListResponse> {
            override fun onResponse(
                call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>
            ) {
                if (response.isSuccessful) {

                    //assign fourth constructor of PokemonListResponse(results) to responseList
                    val responseList = response.body()?.results

                    responseList?.let {
                        globalPokemonList = responseList

                        for (pokemon in responseList) {
                            fetchPokemonDetails(pokemon.url)
                        }
                    }

                    //Let's sort them before assigning dude!
                    globalPokemonDetailsList = mutablePokemonDetailsList.sortedBy { it.id }
                }
            }

            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }

    fun fetchPokemonDetails(url: String) {

        val call = RetrofitClient.pokemonApiService.getPokemonDetails(url)

        call.enqueue(object : Callback<PokemonDetails> {
            override fun onResponse(
                call: Call<PokemonDetails>,
                response: Response<PokemonDetails>
            ) {
                if (response.isSuccessful) {
                    val pokemonDetails = response.body()

                    pokemonDetails?.let {
                        val thisPokemon = PokemonDetails(
                            it.name,
                            it.height,
                            it.id,
                            it.sprites
                        )

                        mutablePokemonDetailsList.add(thisPokemon)
                    }
                }
            }

            override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {
                // Handle failure
            }
        })
    }

}