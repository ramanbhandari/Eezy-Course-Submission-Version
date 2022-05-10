package comp3350.eezy.application;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.eezy.objects.Stubs.User;
import comp3350.eezy.objects.Stubs.UserList;

public class UserListTest {
    UserList list;
    @Test
    public void addUser() {
        User user1, user2;
        user1 = new User("awesomeEezy@email.com", "EezyUser", "EeZy1234**", "awesome_eezy");
        user2 = new User("example@email.com", "helloWorld", "testPass", "hello_world");
        list = new UserList();

        assertTrue(list.addUser(user1));
        assertFalse(list.addUser(user1));
        assertTrue(list.addUser(user2));
    }

    @Test
    public void checkEmail() {
        User user1, user2;
        user1 = new User("group6@email.com", "Group6", "group6%", "group_6");
        user2 = new User("theEmail@email.com", "noName", "Noname123", "no_username");
        list = new UserList();

        assertTrue(list.addUser(user1));
        assertFalse(list.checkEmail(user1.getEmail()));
        assertTrue(list.checkEmail(user2.getEmail()));
        assertTrue(list.addUser(user2));
        assertFalse(list.checkEmail(user2.getEmail()));
    }

    @Test
    public void checkUsername() {
        User user1, user2, user3;
        user1 = new User("googlepx@email.com", "Pixel5", "googlerocks%", "google_pixel");
        user2 = new User("samsung@email.com", "Samsung", "SamsungS12", "google_pixel");
        user3 = new User("apple@email.com", "Apple", "iPhone13ProMAXLEGENDARY", "apple_iphone");
        list = new UserList();

        assertTrue(list.addUser(user1));
        assertTrue(list.checkUsername("google_pixel"));
        assertFalse(list.addUser(user2));
        assertTrue(list.addUser(user3));
        assertTrue(list.checkUsername("apple_iphone"));
    }

    @Test
    public void getSize() {
        list = new UserList();
        final int size = 100;

        for(int i = 0; i < size; i++)
            list.addUser(new User("example" + String.valueOf(i) +"@email.com", "helloWorld" + String.valueOf(i), "testPass" + String.valueOf(i), "hello_world" + String.valueOf(i)));

        assertTrue(list.getSize() == size);
    }
}