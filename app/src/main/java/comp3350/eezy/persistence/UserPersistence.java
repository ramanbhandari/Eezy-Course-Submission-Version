package comp3350.eezy.persistence;

import java.util.List;
import comp3350.eezy.objects.EezyUser;

public interface UserPersistence {
    List<EezyUser> getUserSequential();
    EezyUser insertUser(EezyUser currentUser);
    void deleteUser(EezyUser currentUser);
    EezyUser checkDuplicate(EezyUser currentUser);
}

