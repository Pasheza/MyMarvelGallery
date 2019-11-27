package com.example.mymarvelgallery.view.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarvelgallery.R
import com.example.mymarvelgallery.view.common.ItemAdapter
import com.example.mymarvelgallery.view.common.bindView
import com.example.mymarvelgallery.view.common.loadImage
import com.example.mymarvelgallery.model.MarvelCharacter

class CharacterItemAdapter(
    val character: MarvelCharacter // 1
) : ItemAdapter<CharacterItemAdapter.ViewHolder>(R.layout.item_character) {

    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    override fun ViewHolder.onBindViewHolder() { // 2
        textView.text = character.name
        imageView.loadImage(character.imageUrl) // 3
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView by bindView<TextView>(R.id.textView) // 4
        val imageView by bindView<ImageView>(R.id.imageView) // 4
    }
}