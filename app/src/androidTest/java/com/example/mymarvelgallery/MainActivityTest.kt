package com.example.mymarvelgallery

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.mymarvelgallery.Example.exampleCharacterList
import com.example.mymarvelgallery.data.MarvelRepository
import com.example.mymarvelgallery.view.main.MainActivity

import io.reactivex.Single
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        MarvelRepository.override = BaseMarvelRepository(
            onGetCharacters = { query -> Single.just(exampleCharacterList.filter { query == null || query in it.name }) }
        )
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun There_is_character_list_visible() {
        assertIsVisibleText(Example.exampleCharacter.name)
    }

    @Test
    fun I_see_only_searched_character_after_I_type_its_name_in_search_view() {
        onView(withId(R.id.searchView))
            .perform(replaceText(Example.exampleCharacter.name.take(4)), closeSoftKeyboard())
        assertIsVisibleText(Example.exampleCharacter.name)

        val randomText = "RandomText"
        assert(randomText !in Example.exampleCharacter.name)
        onView(withId(R.id.searchView))
            .perform(replaceText(randomText), closeSoftKeyboard())
        assertIsNotVisibleText(Example.exampleCharacter.name)
    }

    @Test
    fun On_character_clicked_there_is_character_activity_opened() {
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.character_detail_layout))
            .check(matches(isDisplayed()))
    }

    private fun assertIsVisibleText(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    private fun assertIsNotVisibleText(text: String) {
        try {
            onView(withText(text)).check(matches(Matchers.not(isDisplayed())))
        } catch (e: NoMatchingViewException) {
            // View is not in hierarchy
        }
    }
}