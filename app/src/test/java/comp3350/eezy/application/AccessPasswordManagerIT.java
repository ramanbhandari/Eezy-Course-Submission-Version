package comp3350.eezy.application;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import comp3350.eezy.objects.PasswordManager;
import comp3350.eezy.persistence.hsqldb.PasswordManagerPersistenceHSQLDB;
import comp3350.eezy.utils.TestUtils;

public class AccessPasswordManagerIT extends TestCase {
    private AccessPasswordManager accessPasswords;
    private PasswordManager password1;
    private PasswordManager password2;
    private PasswordManager password3;

    @Before
    public void setUp() throws IOException
    {
        accessPasswords = new AccessPasswordManager(new PasswordManagerPersistenceHSQLDB(TestUtils.getDB().getAbsolutePath()));
        password1 = new PasswordManager("jon","Amazon", "jonB", "jonboisvert");
        password2 = new PasswordManager("rob","Facebook", "rob", "robguderian");
        password3 = new PasswordManager("eezy","Eezy", "eezy", "eezy");
    }

    @Test
    public void testAccessPasswordManagerNotNull() { assertNotNull(accessPasswords); }

    @Test
    public void testAccessPasswordManagerInsert()
    {
        PasswordManager p1Inserted = accessPasswords.insertPassword(password1);
        assertSame(p1Inserted, password1);

        PasswordManager p2Inserted = accessPasswords.insertPassword(password2);
        assertSame(p2Inserted, password2);

        PasswordManager p3Inserted = accessPasswords.insertPassword(password3);
        assertSame(p3Inserted, password3);
    }

    @Test
    public void testAccessPasswordManagerDelete()
    {
        //list before any insertions
        List<PasswordManager> passwordManagerList = accessPasswords.getPasswords();
        assertFalse(passwordManagerList.contains(password2));
        assertTrue(passwordManagerList.size() == 0);

        accessPasswords.insertPassword(password1);
        accessPasswords.insertPassword(password2);

        //list after adding some passwords
        passwordManagerList = accessPasswords.getPasswords();
        assertTrue(passwordManagerList.contains(password1));
        assertTrue(passwordManagerList.contains(password2));

        accessPasswords.insertPassword(password3);
        accessPasswords.deletePassword(password1);
        accessPasswords.deletePassword(password2);

        //list after deleting some passwords
        passwordManagerList = accessPasswords.getPasswords();
        assertTrue(passwordManagerList.contains(password3));
        assertFalse(passwordManagerList.contains(password2));
        assertFalse(passwordManagerList.contains(password1));
    }
}
