package com.example.mymarvelgallery.data.network.dto

class CharacterMarvelDto {
    lateinit var name: String
    lateinit var thumbnail: ImageDto

    val imageUrl: String
        get() = thumbnail.completeImagePath
}