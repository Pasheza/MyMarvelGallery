package com.example.mymarvelgallery.data

import com.example.mymarvelgallery.data.network.MarvelApi
import com.example.mymarvelgallery.data.network.provider.retrofit
import com.example.mymarvelgallery.model.MarvelCharacter
import io.reactivex.Single

class MarvelRepositoryImpl : MarvelRepository {

    val api = retrofit.create(MarvelApi::class.java)

    override fun getAllCharacters(): Single<List<MarvelCharacter>> = api.getCharacters(
        offset = 0,
        limit = elementsOnListLimit
    ).map {
        it.data?.results?.map(::MarvelCharacter) ?: emptyList()
    }

    companion object {
        const val elementsOnListLimit = 50
    }
}