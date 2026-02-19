package com.example.rickproject.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.rickproject.presentation.viewmodels.CharactersViewModel

@Composable
fun CharacterDetail(
    vm: CharactersViewModel
){
    val character by vm.selectedCharacter.collectAsState()

    character?.let {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            AsyncImage(
            model = it.image,
            contentDescription = it.name,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
            Text(it.name)
            Text(it.gender)
            Text(it.status)
        }
    }
}

