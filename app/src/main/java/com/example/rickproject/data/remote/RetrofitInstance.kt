package com.example.rickproject.data.remote
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val Base_URL = "https://rickandmortyapi.com/api/"

    val api: RickApiService by lazy {
        Retrofit.Builder().baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.
            create()
            )
            .build().create(RickApiService::class.java)
    }
}