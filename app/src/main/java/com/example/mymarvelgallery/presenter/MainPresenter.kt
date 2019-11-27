package com.example.mymarvelgallery.presenter

import com.example.mymarvelgallery.data.MarvelRepository
import com.example.mymarvelgallery.data.applySchedulers
import com.example.mymarvelgallery.data.plusAssign
import com.example.mymarvelgallery.data.subscribeBy
import com.example.mymarvelgallery.view.main.MainView

class MainPresenter(val view: MainView, val repository: MarvelRepository) : BasePresenter() {

    fun onViewCreated() {
        loadCharacters()
    }

    fun onRefresh() {
        loadCharacters()
    }

    private fun loadCharacters() {
        subscriptions += repository.getAllCharacters()
            .applySchedulers()
            .doOnSubscribe { view.refresh = true }
            .doFinally { view.refresh = false }
            .subscribeBy(
                onSuccess = view::show,
                onError = view::showError
            )
    }
}