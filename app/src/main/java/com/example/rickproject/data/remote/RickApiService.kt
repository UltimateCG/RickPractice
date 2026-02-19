package com.example.rickproject.data.remote

import com.example.rickproject.domain.models.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickApiService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterResponse
}