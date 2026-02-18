package com.example.rickproject.data.remote

import com.example.rickproject.domain.models.CharacterResponse
import retrofit2.http.GET

interface RickApiService {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}