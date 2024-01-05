package androidsamples.java.dicegames;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.accessibility.AccessibilityChecks;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static org.hamcrest.CoreMatchers.containsString;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {
  @Rule
  public ActivityScenarioRule<WalletActivity> activityRule = new ActivityScenarioRule<>(WalletActivity.class);

  @BeforeClass
  public static void enableAccessibilityChecks() {
    AccessibilityChecks.enable();
  }

  @Test
  public void dummyTest_findsColorContrastError() {
    onView(withId(R.id.btn_launch_twoormore)).perform(click());
  }

  /* My own test cases start from here */

  @Test
  public void findsColorContrastError2() {
    onView(withId(R.id.btn_launch_twoormore)).perform(click());
    onView(withId(R.id.btn_info)).perform(click());
    onView(withId(R.id.rbtn_back)).perform(click());
  }


  @Test
  public void checkTouchTargetSize() {
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_launch_twoormore)).perform(click());
    onView(withId(R.id.btn_go)).perform(click());
    onView(withId(R.id.btn_info)).perform(click());
    onView(withId(R.id.rbtn_back)).perform(click());
    onView(withId(R.id.btn_back)).perform(click());
  }
  @Test
  public void infoPageTest(){
    onView(withId(R.id.btn_launch_twoormore)).perform(click());

    onView((withId(R.id.btn_info))).perform(click());

    onView(withId(R.id.rules_info_content)).check(matches(withSubstring("Dice Games Overview")));

  }
  @Test
  public void checkInputElement1() {
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_die)).perform(click());
    onView(withId(R.id.btn_launch_twoormore)).perform(click());
    onView(withId(R.id.edit_wager)).perform(clearText(), typeText("100111"), closeSoftKeyboard());
    onView(withId(R.id.btn_go)).perform(click());
    onView(withId(R.id.btn_back)).perform(click());
  }


}