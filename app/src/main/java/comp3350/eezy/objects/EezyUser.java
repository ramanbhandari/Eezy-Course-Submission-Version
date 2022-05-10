package comp3350.eezy.objects;

public class EezyUser {
    private String name;
    private String username;
    private String email;
    private String password;

    public EezyUser(String name, String username, String email, String password){
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getName(){
        return this.name;
    }

    public boolean equals(Object other) {
        boolean result = false;

        if (other instanceof EezyUser) {
            EezyUser theUser = (EezyUser) other;
            result = this.getUsername().equals(theUser.getUsername())
                    && this.getName().equals(theUser.getName())
                    && this.getEmail().equals(theUser.getEmail())
                    && this.getPassword().equals(theUser.getPassword());
        }
        return result;
    }
}
