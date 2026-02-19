package com.example.rickproject.navigation

sealed class Screen(val route: String) {
    object List: Screen("CharactersScreen")
    object Detail: Screen("CharactersDetails")
}