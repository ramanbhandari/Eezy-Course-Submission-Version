package comp3350.eezy.objects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import comp3350.eezy.application.AccessUsers;
import comp3350.eezy.persistence.UserPersistence;

public class EezyUserTest extends TestCase {

    UserPersistence users;
    AccessUsers user;
    EezyUser user0, user1;

    @Before
    public void setUp()
    {
        users = mock(UserPersistence.class);
        user = new AccessUsers(users);
    }

    @Test
    public void testNotNull()
    {
        assertNotNull(user);
    }

    @Test
    public void testEezyUserGetters()
    {
        EezyUser newUser1 = new EezyUser("remmy", "remmy", "remmy@remmy.com", "remmy");
        EezyUser newUser2 = new EezyUser("eezy", "eezy", "eezy@eezy.com", "eezy");
        final ArrayList<EezyUser> userList = new ArrayList<>();
        userList.add(newUser1);
        userList.add(newUser2);
        when(users.getUserSequential()).thenReturn(userList);
        assertEquals(users.getUserSequential().size(), 2);
        verify(users).getUserSequential();

        EezyUser user0 = new EezyUser("Ironman", "IronMan", "iMan@gmail.com", "iamIronMan");
        EezyUser user1 = new EezyUser("Batman", "batMan", "bMan@gmail.com", "iamBatMan");

        assertNotNull(user0);
        assertNotNull(user1);

        assertSame("Ironman", user0.getName());
        assertSame("IronMan", user0.getUsername());
        assertSame("iMan@gmail.com", user0.getEmail());
        assertSame("iamIronMan", user0.getPassword());
        assertNotSame("Hulk", user0.getUsername());

        assertNotSame("Ironman", user1.getName());
        assertNotSame("bMan@gmail.com", user1.getUsername());
        assertSame("bMan@gmail.com", user1.getEmail());

    }

    @Test
    public void testEezyUserEquals()
    {
        EezyUser newUser1 = new EezyUser("remmy", "remmy", "remmy@remmy.com", "remmy");
        EezyUser newUser2 = new EezyUser("remmy", "remmy", "remmy@remmy.com", "remmy");
        boolean check = newUser1.equals(newUser2);
        final ArrayList<EezyUser> userList = new ArrayList<>();
        userList.add(newUser1);
        when(users.getUserSequential()).thenReturn(userList);
        assertEquals(users.getUserSequential().contains(newUser1), true);
        assertEquals(check, true);
        verify(users).getUserSequential();

        EezyUser user0 = new EezyUser("Ryuk", "RyukSinigami", "ryuk@deathnote.com", "theRyuk");
        EezyUser user1 = new EezyUser("Boruto", "boruto15", "boruto@deathnote.com", "theBoruto");
        assertFalse(user0.equals(user1));

        user0 = new EezyUser("Gon", "GonTheHunter", "gon@hunter.com", "iamGon");
        user1 = new EezyUser("Gon", "GonTheHunter", "gonn@hunter.com", "iamGon");
        assertFalse(user0.equals(user1));

        user0 = new EezyUser("Killua", "KilluaTheAssasin", "killua@hunter.com", "IamKillua");
        user1 = new EezyUser("Killua", "KilluaTheAssasin", "killua@hunter.com", "IamKillua");
        assertTrue(user0.equals(user1));

    }
}
