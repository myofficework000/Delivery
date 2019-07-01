package net.app.delivery.util

import android.view.View
import android.widget.ProgressBar
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher


object ViewActionsEx {

    @JvmStatic
    fun waiting(milliSec: Long) = object : ViewAction {
        override fun getDescription(): String = "waiting $milliSec milli seconds"

        override fun getConstraints(): Matcher<View> = ViewMatchers.isDisplayed()

        override fun perform(uiController: UiController, view: View) =
                uiController.loopMainThreadForAtLeast(milliSec)
    }

    @JvmStatic
    fun setProgress(value: Int) = object : ViewAction {
        override fun getDescription(): String = "set progress to $value"

        override fun getConstraints(): Matcher<View> = ViewMatchers.isAssignableFrom(ProgressBar::class.java)

        override fun perform(uiController: UiController, view: View) {
            (view as ProgressBar).progress = value
        }
    }

}