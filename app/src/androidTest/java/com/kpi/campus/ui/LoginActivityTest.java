package com.kpi.campus.ui;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.kpi.campus.R;
import com.kpi.campus.ui.activity.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.kpi.campus.custom.OrientationChangeAction.orientationLandscape;
import static com.kpi.campus.custom.OrientationChangeAction.orientationPortrait;

/**
 * Tests to verify that the behavior of {@link LoginActivityTest} is correct.
 * Created by Administrator on 29.02.2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    private final String SHORT_CLASS_NAME = ".ui.activity.MainActivity";

    @Rule
    public IntentsTestRule<LoginActivity> mIntentsRule = new IntentsTestRule<>(
            LoginActivity.class);

    /**
     * Tests that user can enter credentials
     */
    @Test
    public void testInputCredentials() {
        inputCredentials("mylogin", "mypassword");
    }

    /**
     * Tests that screen is rotated without exceptions
     */
    @Test
    public void testRotateScreen() {
        onView(isRoot()).perform(orientationLandscape());
        onView(isRoot()).perform(orientationPortrait());
    }

    /**
     * Tests that correct intent is sent by clicking on a login button
     */
    @Test
    public void testSendIntent() {
        inputCredentials("mky", "mky");
        onView(withId(R.id.button_login)).perform(click());
        // Asserts that the given component class name matches intent sent by the application under test.
        intended(hasComponent(hasShortClassName(SHORT_CLASS_NAME)));
    }

//    @Test
//    public void testShowToast() {
//        inputCredentials("faillogin", "failpass");
//        onView(withId(R.id.button_login)).perform(click());
//        onView(withText(R.string.login_failed)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
//    }

    /**
     * Helper method
     * @param login
     * @param password
     */
    private void inputCredentials(String login, String password) {
        onView(withId(R.id.edit_text_input_login)).perform(typeText(login), closeSoftKeyboard());
        onView(withId(R.id.edit_text_input_password)).perform(typeText(password), closeSoftKeyboard());
    }
}
