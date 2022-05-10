package comp3350.eezy.application;

import java.util.ArrayList;
import java.util.List;
import comp3350.eezy.business.Services;
import comp3350.eezy.objects.EezyUser;
import comp3350.eezy.persistence.UserPersistence;

public class AccessUsers {
    private UserPersistence userPersistence;
    private List<EezyUser> users;
    private EezyUser eezyUser;
    private int currentUser;

    public AccessUsers(){
        userPersistence = Services.getUserPersistence();
        users = null;
        eezyUser = null;
        currentUser = 0;
    }
    public AccessUsers(final UserPersistence userPersistence){
        this();
        this.userPersistence = userPersistence;
    }

    public List<EezyUser> getUsers(){
        users = userPersistence.getUserSequential();
        List<EezyUser> usersList = new ArrayList<>(users);
        return usersList;
    }

    public EezyUser getSequential()
    {
        if(users == null)
        {
            users = userPersistence.getUserSequential();
            currentUser = 0;
        }
        if(currentUser < users.size())
        {
            eezyUser = (EezyUser) users.get(currentUser);
            currentUser++;
        }
        else
        {
            users = null;
            eezyUser = null;
            currentUser = 0;
        }
        return eezyUser;
    }

    public EezyUser insertUser(EezyUser currentUser){
        return userPersistence.insertUser(currentUser);
    }

    public EezyUser checkDuplicateUser(EezyUser currentUser) {
        return userPersistence.checkDuplicate(currentUser);
    }

    public void deleteUser(EezyUser currentUser) {
        userPersistence.deleteUser(currentUser);
    }
}
