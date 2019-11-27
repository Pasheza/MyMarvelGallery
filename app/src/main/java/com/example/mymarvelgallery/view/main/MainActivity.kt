package com.example.mymarvelgallery.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymarvelgallery.R
import com.example.mymarvelgallery.data.MarvelRepository
import com.example.mymarvelgallery.model.MarvelCharacter
import com.example.mymarvelgallery.presenter.MainPresenter
import com.example.mymarvelgallery.view.common.BaseActivityWithPresenter
import com.example.mymarvelgallery.view.common.bindToSwipeRefresh
import com.example.mymarvelgallery.view.common.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivityWithPresenter(), MainView {

    override var refresh by bindToSwipeRefresh(R.id.swipeRefreshView)

    override val presenter by lazy { MainPresenter(this, MarvelRepository.get()) }

    private val characters = listOf(
        MarvelCharacter(
            name = "3-D Man", imageUrl =
            "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
        ),
        MarvelCharacter(
            name = "Abomination (Emil Blonsky)", imageUrl =
            "https://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        swipeRefreshView.setOnRefreshListener { presenter.onRefresh() }
        presenter.onViewCreated()
    }

    override fun show(items: List<MarvelCharacter>) {
        val categoryItemAdapters = items.map(::CharacterItemAdapter)
        recyclerView.adapter = MainListAdapter(categoryItemAdapters)
    }

    override fun showError(error: Throwable) {
        toast("Error: ${error.message}") // 2
        error.printStackTrace()
    }
}
