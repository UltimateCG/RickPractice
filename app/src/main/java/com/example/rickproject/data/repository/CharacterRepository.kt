package com.example.rickproject.data.repository
import com.example.rickproject.domain.models.Character
import com.example.rickproject.data.remote.RetrofitInstance

class CharacterRepository {
    suspend fun getCharacters(): List<Character> {
        //Obtencion de datos de la api
        return RetrofitInstance.api.getCharacters().results //Devuelve unicamente datos de los personajes (results)
    }
}