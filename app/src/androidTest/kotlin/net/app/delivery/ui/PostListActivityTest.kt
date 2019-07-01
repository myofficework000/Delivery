package net.app.delivery.ui

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import net.app.delivery.R
import net.app.delivery.ui.post.PostListActivity
import net.app.delivery.ui.post.PostListAdapter
import net.app.delivery.util.ViewActionsEx
import org.hamcrest.Matcher
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PostListActivityTest {


    @get:Rule
    val rule = object : ActivityTestRule<PostListActivity>(PostListActivity::class.java) {}

    @Test
    fun add() {
        // given
        val givenNewContent = "new content"

        // then
        Espresso.onView(withId(R.id.post_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<PostListAdapter.ViewHolder>(0
                        , object : ViewAction {
                    override fun getDescription(): String = "check added memo content is \"$givenNewContent\""

                    override fun getConstraints(): Matcher<View> = hasDescendant(withText(givenNewContent))

                    override fun perform(uiController: UiController?, view: View?) {
                        @Suppress("UNCHECKED_CAST")
                        val matcher = constraints as Matcher<in View?>
                        Assert.assertThat(view, matcher)
                    }

                }))



    }


    @Test
    fun edit() {
        // given
        val givenIdxWillBeEdit = 3
        val givenReplaceText = "replaced!!"


        // when
        Espresso.onView(withId(R.id.post_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<PostListAdapter.ViewHolder>(givenIdxWillBeEdit
                        , object : ViewAction {
                    override fun getDescription(): String = "get before content"

                    override fun getConstraints(): Matcher<View> = isDisplayed()

                    override fun perform(uiController: UiController, view: View) {
                        view.findViewById<EditText>(R.id.post_title).setText(givenReplaceText)
                    }
                }))


        Espresso.onView(withId(R.id.post_list))  // scrolling
                .perform(ViewActionsEx.waiting(500)
                        , RecyclerViewActions.scrollToPosition<PostListAdapter.ViewHolder>(15)
                        , ViewActionsEx.waiting(500)
                        , RecyclerViewActions.scrollToPosition<PostListAdapter.ViewHolder>(0)
                        , ViewActionsEx.waiting(500)
                )


        // then
        Espresso.onView(withId(R.id.post_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<PostListAdapter.ViewHolder>(givenIdxWillBeEdit
                        , object : ViewAction {
                    override fun getDescription(): String = "check replaced text"

                    override fun getConstraints(): Matcher<View> = isDisplayed()

                    override fun perform(uiController: UiController, view: View) {
                        val content = view.findViewById<TextView>(R.id.post_title).text.toString()
                        Assert.assertEquals(givenReplaceText, content)
                    }
                }))

    }


}