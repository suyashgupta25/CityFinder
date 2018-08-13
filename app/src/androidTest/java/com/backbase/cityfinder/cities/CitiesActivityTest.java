package com.backbase.cityfinder.cities;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.backbase.cityfinder.R;
import com.backbase.cityfinder.ui.cities.CitiesActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
@SuppressWarnings("deprecation")
public class CitiesActivityTest {

    @Rule
    public ActivityTestRule<CitiesActivity> rule = new ActivityTestRule<>(CitiesActivity.class);
    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = rule.getActivity().getCitiesListIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void shouldBeAbleToLoadCities() {
        onView(withId(R.id.rl_cities_list)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldBeAbleToScrollCities() {
        onView(withId(R.id.rl_cities_list)).check(matches(isDisplayed()));
        onView(withId(R.id.rl_cities_list)).perform(swipeUp());
        onView(withId(R.id.rl_cities_list)).perform(swipeUp());
    }

    @Test
    public void shouldBeAbleToOpenDetails() {
        onView(withId(R.id.rl_cities_list)).check(matches(isDisplayed()));
        onView(withId(R.id.rl_cities_list)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }
}
