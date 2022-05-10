package comp3350.eezy.objects;

public class PasswordManager{
    private String website;
    private String webUsername;
    private String password;
    private String username;
    
    public PasswordManager(String username, String website, String password, String webUsername){
        this.username = username;
        this.website = website;
        this.webUsername = webUsername;
        this.password = password;
    }

    public String getWebsite(){
        return this.website;
    }

    public String getWebUsername(){
        return this.webUsername;
    }

    public String getPassword(){
        return this.password;
    }
    public String getUsername(){
        return this.username;
    }

    public boolean equals(Object other)
    {
        boolean result = false;
        if(other instanceof PasswordManager)
        {
            PasswordManager otherManager = (PasswordManager) other;
            result = this.getPassword().equals(otherManager.getPassword())
                    && this.getUsername().equals(otherManager.getUsername())
                    && this.getWebsite().equals(otherManager.getWebsite())
                    && this.getWebUsername().equals(otherManager.getWebUsername());
        }
        return result;
    }
}
