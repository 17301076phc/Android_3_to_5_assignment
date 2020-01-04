package com.example.firstassignment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import android.content.Intent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class GuideActivityTest {

    @Rule
    public ActivityTestRule<GuideActivity> guidetest=new ActivityTestRule<GuideActivity>(GuideActivity.class);

    @Test
    public void onCreate() {
    }

    @Test
    public void initWithPageGuideMode() {
        ViewInteraction appCompatViewPager = onView(allOf(withId(R.id.guideviewpager), isDisplayed()));

        appCompatViewPager.perform(swipeLeft());
        appCompatViewPager.perform(swipeLeft());
        appCompatViewPager.perform(swipeLeft());
//左滑三次或者直接跳过 进入MainActivity
    //    ViewInteraction appCompatButton = onView(allOf(withText("跳过"), isDisplayed()));
      //  appCompatButton.perform(click());

    }

}