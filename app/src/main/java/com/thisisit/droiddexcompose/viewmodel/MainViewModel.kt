package com.thisisit.droiddexcompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.thisisit.droiddexcompose.model.PokemonListResponse
import com.thisisit.droiddexcompose.model.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    var globalPokemonList by mutableStateOf(emptyList<String>())
        private set

    fun fetchPokemonList() {

        val nameList = mutableListOf<String>()

        val call = RetrofitClient.pokemonApiService.getPokemonList()

        call.enqueue(object : Callback<PokemonListResponse> {
            override fun onResponse(
                call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>
            ) {
                if (response.isSuccessful) {
                    val pokemonList = response.body()?.results
                    pokemonList?.let {
                        for (pokemon in it) {

                            //fetchPokemonDetails(pokemon.url)
                            nameList.add(pokemon.name)
                        }
                    }
                }

                //assign the values of nameList to pokemonList
                globalPokemonList = nameList
            }

            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                // Handle failure
                fetchPokemonList()
            }
        })
    }

//    private fun fetchPokemonDetails(url: String) {
//        val call = RetrofitClient.pokemonApiService.getPokemonDetails(url)
//
//        call.enqueue(object : Callback<Pokemon> {
//            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
//                if (response.isSuccessful) {
//                    val pokemon = response.body()
//                    pokemon?.let {
//                        // Process the Pokemon details
//                        println("Name: ${it.name}, URL: ${it.url}")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
//                // Handle failure
//            }
//        })
//    }

}