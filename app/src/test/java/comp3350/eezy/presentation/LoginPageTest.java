package comp3350.eezy.presentation;

import static org.junit.Assert.*;
import org.junit.Test;
import comp3350.eezy.persistence.CalendarDB;

public class LoginPageTest {
    private CalendarDB db;

    @Test
    public void testLogin()
    {
        LoginPage lp = new LoginPage();
        assert(lp.checkDuplicate("remmy", "remmy") != null);
    }

}