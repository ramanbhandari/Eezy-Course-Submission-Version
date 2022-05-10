package comp3350.eezy.objects;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import comp3350.eezy.application.AccessPasswordManager;
import comp3350.eezy.persistence.PasswordManagerPersistence;

public class PasswordManagerTest extends TestCase {

    PasswordManagerPersistence passwords;
    AccessPasswordManager password;

    @Before
    public void setUp()
    {
        passwords = mock(PasswordManagerPersistence.class);
        password = new AccessPasswordManager(passwords);
    }

    @Test
    public void testNotNull()
    {
        assertNotNull(password);
    }

    @Test
    public void testPasswordManagerGetters()
    {
        PasswordManager p1 = new PasswordManager("eezy","Amazon", "remmy", "remmy");
        PasswordManager p2 = new PasswordManager("eezy","Eezy", "eezy", "eezy");

        assertNotNull(p1);
        assertSame("remmy", p1.getWebUsername());
        assertSame("Amazon", p1.getWebsite());
        assertSame("remmy", p1.getPassword());

        final ArrayList<PasswordManager> passwordList = new ArrayList<>();
        passwordList.add(p1);
        passwordList.add(p2);
        when(passwords.getPasswordManagerSequential()).thenReturn(passwordList);
        assertEquals(passwords.getPasswordManagerSequential().size(), 2);
        verify(passwords).getPasswordManagerSequential();
    }

    @Test
    public void testEventEquals()
    {

        PasswordManager p1 = new PasswordManager("eezy","Ebay", "eezy", "eezy");
        PasswordManager p2 = new PasswordManager("eezy","Ebay", "eezy", "eezy");

        boolean check = p1.equals(p2);
        final ArrayList<PasswordManager> passwordList = new ArrayList<>();
        passwordList.add(p1);
        when(passwords.getPasswordManagerSequential()).thenReturn(passwordList);
        assertEquals(passwords.getPasswordManagerSequential().contains(p1), true);
        assertEquals(check, true);
        verify(passwords).getPasswordManagerSequential();

        PasswordManager p3 = new PasswordManager("abhi", "Google", "abhiS", "AbhiS");
        PasswordManager p4 = new PasswordManager("dhariya", "Google", "dhairyaP", "DhairyaP");
        assertFalse(p3.equals(p4));

    }
}

