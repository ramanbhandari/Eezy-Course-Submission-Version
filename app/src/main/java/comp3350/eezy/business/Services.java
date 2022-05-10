package comp3350.eezy.business;

import comp3350.eezy.persistence.CalendarPersistence;
import comp3350.eezy.persistence.PasswordManagerPersistence;
import comp3350.eezy.persistence.UserPersistence;
import comp3350.eezy.persistence.hsqldb.CalendarPersistenceHSQLDB;
import comp3350.eezy.persistence.hsqldb.PasswordManagerPersistenceHSQLDB;
import comp3350.eezy.persistence.hsqldb.UserPersistenceHSQLDB;

public class Services {

    public static CalendarPersistence calendarPersistence = null;
    public static PasswordManagerPersistence passwordManagerPersistence = null;
    public static UserPersistence userPersistence = null;

    public static synchronized CalendarPersistence getCalendarPersistence(){
        if(calendarPersistence == null){
            calendarPersistence = new CalendarPersistenceHSQLDB(Main.getDBPathName());
        }
        return calendarPersistence;
    }
    public static synchronized PasswordManagerPersistence getPasswordManagerPersistence(){
        if(passwordManagerPersistence == null){
            passwordManagerPersistence = new PasswordManagerPersistenceHSQLDB(Main.getDBPathName());
        }
        return passwordManagerPersistence;
    }
    public static synchronized UserPersistence getUserPersistence(){
        if(userPersistence == null){
            userPersistence = new UserPersistenceHSQLDB(Main.getDBPathName());
        }
        return userPersistence;
    }
}
