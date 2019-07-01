package net.app.delivery.ui.post


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import net.app.delivery.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PostListActivityTest2 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(PostListActivity::class.java)

    @Test
    fun postListActivityTest2() {
        val recyclerView = onView(
                allOf(withId(R.id.post_list),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()))
        recyclerView.check(matches(isDisplayed()))

        val recyclerView2 = onView(
                allOf(withId(R.id.post_list),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()))
        recyclerView2.check(matches(isDisplayed()))

        val viewGroup = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.post_list),
                                3),
                        0),
                        isDisplayed()))
        viewGroup.check(matches(isDisplayed()))

        val viewGroup2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.post_list),
                                4),
                        0),
                        isDisplayed()))
        viewGroup2.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
