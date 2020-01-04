package com.example.firstassignment;

import android.support.test.InstrumentationRegistry;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.view.menu.MenuView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.bumptech.glide.GenericTransitionOptions.with;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class listcourseTest {

    @Rule
    public ActivityTestRule<listcourse> listcousetest=new ActivityTestRule<listcourse>(listcourse.class);

    @Test
    public void onCreate() {
        onView(allOf(withId(R.id.toolbar),isDisplayed())).perform();
        onView(allOf(withId(R.id.fragment_content), isDisplayed())).perform(swipeDown());
        onView(allOf(withId(R.id.fragment_content), isDisplayed())).perform(swipeUp());

        onView(withId(R.id.draw_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation_view)).perform(NavigationViewActions.navigateTo(R.id.nv_msg));
        Espresso.pressBack();


       // onView(withId(R.id.bottom_view)).perform(NavigationViewActions.navigateTo(R.id.item_3));  页面切换存在动画

    }

}