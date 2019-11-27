package com.example.mymarvelgallery.helpers

import com.example.mymarvelgallery.data.MarvelRepository
import com.example.mymarvelgallery.model.MarvelCharacter

import io.reactivex.Single

class BaseMarvelRepository(
        val onGetCharacters: () -> Single<List<MarvelCharacter>>
) : MarvelRepository {

    override fun getAllCharacters() = onGetCharacters()
}

