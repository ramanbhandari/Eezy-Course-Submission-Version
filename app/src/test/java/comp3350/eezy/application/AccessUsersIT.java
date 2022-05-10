package comp3350.eezy.application;
import junit.framework.TestCase;

import org.junit.Test;
import java.io.IOException;
import java.util.List;

import comp3350.eezy.objects.EezyUser;
import comp3350.eezy.persistence.hsqldb.UserPersistenceHSQLDB;
import comp3350.eezy.utils.TestUtils;

public class AccessUsersIT extends TestCase {
    private AccessUsers accessUsers;
    private EezyUser user0;
    private EezyUser user1;
    private EezyUser user2;
    private EezyUser user3;
    private EezyUser user4;

    public void setUp() throws IOException
    {
        accessUsers = new AccessUsers(new UserPersistenceHSQLDB(TestUtils.getDB().getAbsolutePath()));
        user0 = new EezyUser("Remmy", "remmyRemmy", "remmy@gmail.com", "eezyRemmy");
        user1 = new EezyUser("Dhariya", "dhariyap", "dhariya@gmail.com", "eezyDhariya");
        user2 = new EezyUser("Abhi", "abhiS", "abhi@gmail.com", "eezyAbhi");
        user3 = new EezyUser("Kunal", "kunalR", "kunal@gmail.com", "eezyKunal");
        user4 = new EezyUser("Winson", "WinsonL", "winson@gmail.com", "eezyWinson");
    }

    @Test
    public void testAccessUsersNotNull(){assertNotNull(accessUsers);}

    @Test
    public void testAccessUsersInsert()
    {
        EezyUser user0Inserted = accessUsers.insertUser(user0);
        assertSame(user0Inserted, user0);

        EezyUser user1Inserted = accessUsers.insertUser(user1);
        assertSame(user1Inserted, user1);

        EezyUser user2Inserted = accessUsers.insertUser(user2);
        assertSame(user2Inserted, user2);

        EezyUser user3Inserted = accessUsers.insertUser(user3);
        assertSame(user3Inserted, user3);

        EezyUser user4Inserted = accessUsers.insertUser(user4);
        assertSame(user4Inserted, user4);
    }

    @Test
    public void testAccessUsersDelete()
    {
        //list before any insertions
        List<EezyUser> userList = accessUsers.getUsers();
        assertFalse(userList.contains(user0));

        accessUsers.insertUser(user0);
        accessUsers.insertUser(user1);
        accessUsers.insertUser(user2);

        //list after some insertions
        userList = accessUsers.getUsers();
        assertTrue(userList.contains(user0));
        assertTrue(userList.contains(user1));
        assertTrue(userList.contains(user2));

        accessUsers.deleteUser(user0);
        accessUsers.deleteUser(user2);

        //list after some deletions
        userList = accessUsers.getUsers();
        assertFalse(userList.contains(user0));
        assertTrue(userList.contains(user1));
        assertFalse(userList.contains(user2));
    }

    @Test
    public void testAccessUsersGetUsers()
    {
        accessUsers.insertUser(user0);
        accessUsers.insertUser(user1);
        accessUsers.insertUser(user2);
        accessUsers.insertUser(user3);
        accessUsers.insertUser(user4);

        List<EezyUser> users = accessUsers.getUsers();
        assertTrue(users.contains(user0));
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
        assertTrue(users.contains(user3));
        assertTrue(users.contains(user4));
    }

    @Test
    public void testAccessUsersGetSequential()
    {
        List<EezyUser> users = accessUsers.getUsers();
        for(EezyUser user : users)
            assertSame(accessUsers.getSequential(), user);
    }

    @Test
    public void testAccessUsersDuplicate()
    {
        //when there are no users in the list
        assertNull(accessUsers.checkDuplicateUser(user0));

        //inserting some users
        assertNotNull(accessUsers.insertUser(user0));
        assertNotNull(accessUsers.insertUser(user1));
        assertNotNull(accessUsers.insertUser(user2));

        //checking some duplicate users
        assertNotNull(accessUsers.checkDuplicateUser(user1));
        assertNotNull(accessUsers.checkDuplicateUser(user2));
    }
}