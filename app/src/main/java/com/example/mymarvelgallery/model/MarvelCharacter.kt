package com.example.mymarvelgallery.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.example.mymarvelgallery.data.network.dto.CharacterMarvelDto
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class MarvelCharacter(
    val name: String,
    val imageUrl: String,
    val description: String,
    val comics: List<String>,
    val series: List<String>,
    val stories: List<String>,
    val events: List<String>
) : Parcelable {
    constructor(dto: CharacterMarvelDto) : this(
        name = dto.name,
        imageUrl = dto.imageUrl,
        description = dto.description,
        comics = dto.comics.items.map { it.name },
        series = dto.series.items.map { it.name },
        stories = dto.stories.items.map { it.name },
        events = dto.events.items.map { it.name }
    )
}