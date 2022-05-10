package comp3350.eezy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import comp3350.eezy.application.UserListTest;
import comp3350.eezy.objects.EezyUserTest;
import comp3350.eezy.objects.EventTest;
import comp3350.eezy.objects.PasswordManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserListTest.class,
        EezyUserTest.class,
        EventTest.class,
        PasswordManagerTest.class
})
public class AllTests {
}
