package com.example.mymarvelgallery.view.common

import androidx.appcompat.app.AppCompatActivity
import com.example.mymarvelgallery.presenter.Presenter

abstract class BaseActivityWithPresenter: AppCompatActivity() {

    abstract val presenter: Presenter

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

}