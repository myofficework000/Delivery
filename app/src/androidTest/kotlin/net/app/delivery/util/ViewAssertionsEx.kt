package net.app.delivery.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewAssertion
import org.junit.Assert


object ViewAssertionsEx {
    @JvmStatic
    fun isInvisible() = ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null ) throw noViewFoundException
        Assert.assertEquals(View.INVISIBLE, view.visibility)
    }

    @JvmStatic
    fun hasItemCountOfRecyclerView(count: Int) = ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null ) throw noViewFoundException
        val v = view as RecyclerView
        Assert.assertEquals(count, v.adapter!!.itemCount)
    }
}