package comp3350.eezy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.eezy.application.AccessEventsIT;
import comp3350.eezy.application.AccessPasswordManagerIT;
import comp3350.eezy.application.AccessUsersIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessUsersIT.class,
        AccessEventsIT.class,
        AccessPasswordManagerIT.class
})

public class IntegrationTests {
}
