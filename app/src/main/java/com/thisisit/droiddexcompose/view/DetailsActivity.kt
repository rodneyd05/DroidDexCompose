package com.thisisit.droiddexcompose.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.thisisit.droiddexcompose.model.PokemonDetails
import com.thisisit.droiddexcompose.view.ui.theme.DroidDexComposeTheme

@Suppress("DEPRECATION")
class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemonDetails = intent.getParcelableExtra<PokemonDetails>(MainActivity.PASS_DETAILS)

        setContent {
            DroidDexComposeTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    PokemonCard(pokemonDetails)
                }
            }
        }
    }
}

@Composable
fun PokemonCard(pokemonDetails: PokemonDetails?) {

    val formattedId = String.format("%04d", pokemonDetails!!.id)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val name = pokemonDetails.name

            Text(
                text = name[0].uppercase() + name.substring(1, name.length),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = formattedId
            )
            Image(
                painter = rememberAsyncImagePainter(
                    pokemonDetails.sprites.other.official_artwork.front_default
                ),
                contentDescription = pokemonDetails.name
            )
        }
    }
}