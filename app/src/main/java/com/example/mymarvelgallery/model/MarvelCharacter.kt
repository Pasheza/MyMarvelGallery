package com.example.mymarvelgallery.model

import com.example.mymarvelgallery.data.network.dto.CharacterMarvelDto

data class MarvelCharacter(
    val name: String,
    val imageUrl: String
) {
    constructor(dto: CharacterMarvelDto) : this(
        name = dto.name,
        imageUrl = dto.imageUrl
    )
}