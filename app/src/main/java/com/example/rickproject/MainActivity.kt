package com.example.rickproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.rickproject.presentation.viewmodels.CharactersViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CharactersScreen()
        }
    }
}

@Composable
fun CharactersScreen(vm: CharactersViewModel = viewModel()){

    val characters by vm.characters.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        vm.fetchCharacters()
    }

    LazyColumn {
        items(characters){
            character ->
            Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)){
                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier.size(72.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(character.name, fontSize = 18.sp)
            }
        }
    }
}