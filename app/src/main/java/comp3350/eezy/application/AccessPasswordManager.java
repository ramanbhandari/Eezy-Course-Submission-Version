package comp3350.eezy.application;

import java.util.ArrayList;
import java.util.List;
import comp3350.eezy.business.Services;
import comp3350.eezy.objects.PasswordManager;
import comp3350.eezy.persistence.PasswordManagerPersistence;

public class AccessPasswordManager {

    private PasswordManagerPersistence passwordManagerPersistence;
    private List<PasswordManager> passwords;
    private PasswordManager passwordManager;
    private int currentPassword;

    public AccessPasswordManager(){
        passwordManagerPersistence = Services.getPasswordManagerPersistence();
        passwords = null;
        passwordManager = null;
        currentPassword = 0;
    }

    public AccessPasswordManager(final PasswordManagerPersistence passwordManagerPersistence){
        this();
        this.passwordManagerPersistence = passwordManagerPersistence;
    }

    public List<PasswordManager> getPasswords(){
        passwords = passwordManagerPersistence.getPasswordManagerSequential();
        List<PasswordManager> thePasswords = new ArrayList<>(passwords);
        return thePasswords;
    }

    public PasswordManager insertPassword(PasswordManager currentPassword){
        return passwordManagerPersistence.insertPassword(currentPassword);
    }

    public PasswordManager deletePassword(PasswordManager currentPassword){
        return passwordManagerPersistence.deletePassword(currentPassword);
    }
}
