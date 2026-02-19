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
    private val _selectedCharacter = MutableStateFlow<Character?>(null) //Valor inicial: Null
    //Se asigna a una variable de solo lectura
    val characters: StateFlow<List<Character>> = _characters
    val selectedCharacter: StateFlow<Character?> = _selectedCharacter

    private var currentPage = 1
    private var isLoading = false

    fun loadNext() {
        //Si está cargando, devuelve para evitar spam de solicitudes
        if (isLoading) return
        viewModelScope.launch {

            isLoading = true //Define el estado de carga verdadero para que se efectue la primera condicion
            val newCharacters = repo.getCharacters(currentPage)
            _characters.value += newCharacters //A medida que se cargan personajes de otras páginas, se añaden a la lista total

            currentPage ++

            isLoading = false
        }
    }

    fun selectCharacter(character: Character) {
        _selectedCharacter.value = character
    }

}