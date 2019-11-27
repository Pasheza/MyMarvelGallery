package com.sample.marvelgallery.helpers

import com.example.mymarvelgallery.model.MarvelCharacter

object Example {
    val exampleCharacter = MarvelCharacter("ExampleName", "ExampleImageUrl")
    val exampleCharacterList = listOf(
            exampleCharacter,
            MarvelCharacter("Name1", "ImageUrl1"),
            MarvelCharacter("Name2", "ImageUrl2")
    )
}
