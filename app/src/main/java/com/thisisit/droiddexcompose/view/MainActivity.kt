package com.thisisit.droiddexcompose.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.thisisit.droiddexcompose.model.PokemonDetails
import com.thisisit.droiddexcompose.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Homepage()
        }
    }
}

@Composable
//For duplicate class error
//build.gradle(Project) is set to
//id 'org.jetbrains.kotlin.android' version '1.8.10' apply false
//build.gradle(app)
//kotlinCompilerExtensionVersion '1.4.3'
fun Homepage() {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FeaturedPokemon()
        OtherRegion()
    }
}

//Top part of the Homepage(Featured Pokemon)
@Composable
fun FeaturedPokemon() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(0.3f)
            .padding(10.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            text = "Featured Pokemon"
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {

                //Only 1 image is shown, image is not loaded async
                Image(
                    painter = rememberAsyncImagePainter("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"),
                    contentDescription = "featured_pokemon_image"
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Name: Pikachu")
                Text(text = "ID: 25")
                Text(text = "Height: 4")
                Text(text = "Weight: 60")
            }
        }
    }
}

//Region intended for recyclerview
@Composable
fun OtherRegion() {

    //we can create an instance of viewModel inside the MainActivity by using this code
    //private val mainViewModel by viewModels<MainViewModel>()

    //Since we want to create an instance here in a composable and it is not accessible here, we add dependency
    //implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2"
    val mainViewModel = viewModel<MainViewModel>()
    mainViewModel.fetchPokemonList()

    //calling this updates the state of responseList in MainViewModel
    mainViewModel.globalPokemonList

    val pokemonDetailsList = mainViewModel.globalPokemonDetailsList

    RecyclerView(pokemonList = pokemonDetailsList)
}

@Composable
fun PokemonItem(pokemonDetails: PokemonDetails) {
    Surface(
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            //Used v2.4.0
            //implementation "io.coil-kt:coil-compose:2.4.0"
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(5.dp)
                    .scale(0.8f),
                model = pokemonDetails.sprites.other.official_artwork.front_default,
                contentDescription = pokemonDetails.name
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                Text(text = "Name: ${pokemonDetails.name}")
                Text(text = "ID: ${pokemonDetails.id}")
                Text(text = "Height: ${pokemonDetails.height}")
                Text(text = "Weight: ${pokemonDetails.weight}")
            }
        }
    }
}

@Composable
fun RecyclerView(pokemonList: List<PokemonDetails>) {
    LazyColumn(modifier = Modifier.padding(10.dp)) {
        items(items = pokemonList) { pokemon ->
            PokemonItem(pokemonDetails = pokemon)
        }
    }
}