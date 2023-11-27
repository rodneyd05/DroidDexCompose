package com.thisisit.droiddexcompose.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thisisit.droiddexcompose.R
import com.thisisit.droiddexcompose.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize the value of Model
        mainViewModel.fetchPokemonList()

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
        modifier = Modifier.fillMaxSize()
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
            .padding(20.dp)
    ) {

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
                Image(
                    modifier = Modifier.fillMaxSize(0.8f),
                    painter = painterResource(id = R.drawable.whos_that_pokemon_logo),
                    contentDescription = "default_pokemon_image"
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Name:")
                Text(text = "ID:")
                Text(text = "Type:")
                Text(text = "Evolution:")
                Text(text = "Effective:")
                Text(text = "Weakness:")
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

    val updatedPokemonList = mainViewModel.globalPokemonList

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Column {

            for (pokemonName in updatedPokemonList) {
                Text(text = pokemonName)
            }

            if (updatedPokemonList.isEmpty()) {
                Text(text = "Loading...")
            }
        }
    }
}