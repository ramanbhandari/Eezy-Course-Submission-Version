package comp3350.eezy.objects.Stubs;

public class User {
    private String email;
    private String name;
    private String password;
    private String username;

    public User(String email, String name, String password, String userName) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.username = userName;
    }

    public String getEmail(){
        return this.email;
    }
    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }
    public String getUserName() {
        return this.username;
    }
}