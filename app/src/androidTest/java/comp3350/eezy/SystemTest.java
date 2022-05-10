package comp3350.eezy;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.*;


import static org.hamcrest.CoreMatchers.anything;

import android.os.SystemClock;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import comp3350.eezy.business.Services;
import comp3350.eezy.objects.EezyUser;
import comp3350.eezy.objects.Event;
import comp3350.eezy.persistence.CalendarPersistence;
import comp3350.eezy.persistence.UserPersistence;
import comp3350.eezy.presentation.LoginPage;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SystemTest {
    @Rule
    public ActivityScenarioRule activityRule =
            new ActivityScenarioRule<LoginPage>(LoginPage.class);

    @Before
    public void setupDataBase(){
        //clear user "Eezy" from the database
        UserPersistence userPersist = Services.getUserPersistence();
        List<EezyUser> users = userPersist.getUserSequential();
        CalendarPersistence calendarPersistence = Services.getCalendarPersistence();
        List<Event> events = calendarPersistence.getEventsSequential();

        for(EezyUser u: users)
            if(u.getUsername().equals("Eezy"))
                userPersist.deleteUser(u);

        for(Event e: events)
            if(e.getUsername().equals("Eezy") && e.getEventName().equals("Our project is being marked, good day!"))
                calendarPersistence.deleteEvent(e);
    }

    @Test
    public void loginUserTest() {
        //login with 'Eezy' as username
        onView(withId(R.id.username)).perform(typeText("Eezy"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("Group6Rocks"));
        closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());
        SystemClock.sleep(5000);
        // We should receive an invalid username pop-up as expected

        //Now let's signup then!!
        onView(withId(R.id.signupButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText("Awesome Group 6"));
        closeSoftKeyboard();
        onView(withId(R.id.email)).perform(typeText("eezy6@comp3350.com"));
        closeSoftKeyboard();
        onView(withId(R.id.username)).perform(typeText("Eezy"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("Group6Rocks"));
        closeSoftKeyboard();
        onView(withId(R.id.confirm_password)).perform(typeText("Group6Rocks"));
        closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());
        SystemClock.sleep(5000);

        //Now we should be able to login
        onView(withId(R.id.username)).perform(typeText("Eezy"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("Group6Rocks"));
        closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());
        SystemClock.sleep(5000);
    }

    @Test
    public void addAndDeleteEventTest () {
        //login with 'Eezy' as username
        onView(withId(R.id.username)).perform(typeText("Eezy"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("Group6Rocks"));
        closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());

        //change to dark mode
        onView(withId(R.id.darkSwitch)).perform(click());
        //go to calendar
        onView(withId(R.id.buttonCalendar)).perform(click());
        onView(withId(R.id.eventName)).perform(typeText("Our project is being marked, good day!"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonSave)).perform(click());
        pressBack();
        onView(withId(R.id.buttonCalendar)).perform(click());
        SystemClock.sleep(5000);

        //Delete the Event
        onView(withId(R.id.buttonDelete)).perform(click());
        pressBack();
        onView(withId(R.id.buttonCalendar)).perform(click());
        SystemClock.sleep(5000);
    }

    @Test
    public void addAndManagePasswordsTest(){
        //login with 'Eezy' as username
        onView(withId(R.id.username)).perform(typeText("remmy"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("remmy"));
        closeSoftKeyboard();
        onView(withId(R.id.button)).perform(click());

        //go to Eezy password manager
        onView(withId(R.id.buttonPassword)).perform(click());
        onView(withId(R.id.buttonAdd)).perform(click());
        onView(withId(R.id.enterUser)).perform(typeText("Netflix"));
        closeSoftKeyboard();
        onView(withId(R.id.enterName)).perform(typeText("eezyUser"));
        closeSoftKeyboard();
        onView(withId(R.id.enterPass)).perform(typeText("secretPassword"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonSave)).perform(click());
        SystemClock.sleep(5000);
        pressBack();
        onView(withId(R.id.buttonManage)).perform(click());
        //Now we should be able to see our passwords saved there
        onData(anything()).inAdapterView(withId(R.id.listCourses)).atPosition(0).perform(click());
        //Try deleting it now
        onView(withId(R.id.buttonDeletePassword)).perform(click());
        SystemClock.sleep(5000);
    }

}
