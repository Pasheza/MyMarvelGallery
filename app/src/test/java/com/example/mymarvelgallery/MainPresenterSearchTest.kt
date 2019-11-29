@file:Suppress("IllegalIdentifier")

package com.example.mymarvelgallery

import com.example.mymarvelgallery.presenter.MainPresenter
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainPresenterSearchTest {

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `When view is created, then search query is null`() {
        assertOnAction { onViewCreated() } searchQueryIsEqualTo null
    }

    @Test
    fun `When text is changed, then we are searching for new query`() {
        for (text in listOf("KKO", "HJ HJ", "And so what?"))
            assertOnAction { onSearchChanged(text) } searchQueryIsEqualTo text
    }

    @Test
    fun `For blank text, there is request with null query`() {
        for (emptyText in listOf("", "   ", "       "))
            assertOnAction { onSearchChanged(emptyText) } searchQueryIsEqualTo null
    }

    private fun assertOnAction(action: MainPresenter.() -> Unit)
            = PresenterActionAssertion(action)

    private class PresenterActionAssertion(val actionOnPresenter: MainPresenter.() -> Unit) {

        infix fun searchQueryIsEqualTo(expectedQuery: String?) {
            var checkApplied = false
            val view = BaseMainView(onShowError = { Assert.fail() })
            val marvelRepository = BaseMarvelRepository { searchQuery ->
                Assert.assertEquals(expectedQuery, searchQuery)
                checkApplied = true
                Single.never()
            }
            val mainPresenter = MainPresenter(view, marvelRepository)
            mainPresenter.actionOnPresenter()
            Assert.assertTrue(checkApplied)
        }
    }

}