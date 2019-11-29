package com.example.mymarvelgallery

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.mymarvelgallery.Example.exampleCharacterList
import com.example.mymarvelgallery.data.MarvelRepository
import com.example.mymarvelgallery.view.main.MainActivity
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CharacterProfileOpeningTest {

    @Rule
    @JvmField val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        MarvelRepository.override = BaseMarvelRepository(
                onGetCharacters = { query -> Single.just(exampleCharacterList.filter { query == null || query in it.name }) }
        )
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun On_character_clicked_there_is_character_activity_opened() {
        onView(withId(R.id.recyclerView))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.character_detail_layout))
                .check(matches(isDisplayed()))
    }
}
