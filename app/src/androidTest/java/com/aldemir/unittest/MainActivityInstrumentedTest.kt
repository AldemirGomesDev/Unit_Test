package com.aldemir.unittest

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.aldemir.unittest.ui.home.MainActivity
import com.aldemir.unittest.ui.home.MainAdapter
import com.aldemir.unittest.ui.login.LoginActivity
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    var rule: ActivityTestRule<LoginActivity> = object : ActivityTestRule<LoginActivity>(
        LoginActivity::class.java
    ) {
        override fun getActivityIntent(): Intent {
            InstrumentationRegistry.getTargetContext()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.putExtra("MYKEY", "Hello")
            return intent
        }
    }

    @Test
    @Throws(Exception::class)
    fun ensureIntentDataIsDisplayed() {
        val activity = activityRule.activity

        val viewById: View =  activity.findViewById(R.id.text_title)

        assertThat(viewById, notNullValue())
        assertThat(viewById, instanceOf(TextView::class.java))
        val textView: TextView = viewById as TextView
        assertEquals(textView.text.toString(), "Hello")
    }


    @Test
    @Throws(Exception::class)
    fun ensureListViewIsPresent() {
        val activity: MainActivity = activityRule.activity
        val viewById: RecyclerView = activity.findViewById(R.id.recyclerView)
        assertThat(viewById, notNullValue())
        assertThat(viewById, instanceOf(RecyclerView::class.java))
        val listView: RecyclerView = viewById as RecyclerView
        val adapter = listView.adapter
        assertThat(adapter, instanceOf(MainAdapter::class.java))

        Log.d("testInstrumented", "list count: ${adapter!!.itemCount}")
        assertEquals(adapter!!.itemCount, 4)
    }


}