package com.example.mymarvelgallery

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.example.mymarvelgallery.view.character.CharacterProfileActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class CharacterProfileActivityTest {

    @Rule
    @JvmField val activityTestRule = ActivityTestRule(CharacterProfileActivity::class.java, false, false)

    @Test
    fun characterActivityTest() {
        val context = getInstrumentation().targetContext
        val character = Example.exampleCharacter
        activityTestRule.launchActivity(CharacterProfileActivity.getIntent(context, character))

        // Character name is displayed
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar))))
                .check(matches(withText(character.name)))

        // Character description is displayed
        onView(withId(R.id.descriptionView))
                .check(matches(withText(character.description)))
    }
}
