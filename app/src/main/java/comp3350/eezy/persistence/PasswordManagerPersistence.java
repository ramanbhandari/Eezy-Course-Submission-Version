package comp3350.eezy.persistence;

import java.util.List;
import comp3350.eezy.objects.PasswordManager;

public interface PasswordManagerPersistence {
    List<PasswordManager> getPasswordManagerSequential();
    PasswordManager insertPassword(PasswordManager currentPassword);
    PasswordManager deletePassword(PasswordManager currentPassword);
}
