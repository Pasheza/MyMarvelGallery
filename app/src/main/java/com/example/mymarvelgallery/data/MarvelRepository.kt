package com.example.mymarvelgallery.data

import com.example.mymarvelgallery.model.MarvelCharacter
import io.reactivex.Single

interface MarvelRepository {
    fun getAllCharacters(searchQuery: String?): Single<List<MarvelCharacter>>

    companion object : Provider<MarvelRepository>(){
        override fun creator(): MarvelRepository = MarvelRepositoryImpl()
    }
}