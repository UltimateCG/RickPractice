package com.example.rickproject.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickproject.data.repository.CharacterRepository
import com.example.rickproject.domain.models.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersViewModel: ViewModel() {
    private val repo = CharacterRepository()
    //Puede cambiar su valor
    private val _characters = MutableStateFlow<List<Character>>(emptyList()) //Valor Inicial: Lista vacía
    //Se asigna a una variable de solo lectura
    val characters: StateFlow<List<Character>> = _characters

    fun fetchCharacters(){
        viewModelScope.launch {
            //Se llama a la funcion del reository y actualiza el Stateflow
            _characters.value = repo.getCharacters()
        }
    }
}