package com.example.rickproject.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickproject.navigation.Screen
import com.example.rickproject.presentation.viewmodels.CharactersViewModel

@Composable
fun CharactersScreen(
    navController: NavController,
    vm: CharactersViewModel){

    val characters by vm.characters.collectAsState(initial = emptyList())
    val listState = rememberLazyListState()
    LaunchedEffect(Unit) {
        vm.loadNext()
    }

    LazyColumn (state = listState) {
        items(characters){
                character ->
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable{
                        vm.selectCharacter(character)
                        navController.navigate("CharactersDetails")
                    }.padding(8.dp)
            ){
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

    LaunchedEffect(listState) {
        snapshotFlow {
            //Atrapa el ultimo elemento de la lista
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastVisible ->
            if (lastVisible == characters.lastIndex){
                vm.loadNext()
            }
        }
    }
}