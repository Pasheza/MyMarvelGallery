package com.example.mymarvelgallery

import com.example.mymarvelgallery.data.MarvelRepository
import com.example.mymarvelgallery.model.MarvelCharacter
import io.reactivex.Single

class BaseMarvelRepository(
    val onGetCharacters: (String?) -> Single<List<MarvelCharacter>>
) : MarvelRepository {

    override fun getAllCharacters(searchQuery: String?) = onGetCharacters(searchQuery)
}