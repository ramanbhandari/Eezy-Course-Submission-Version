package comp3350.eezy.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.eezy.objects.EezyUser;
import comp3350.eezy.objects.Event;
import comp3350.eezy.objects.PasswordManager;
import comp3350.eezy.persistence.PasswordManagerPersistence;

public class PasswordManagerPersistenceHSQLDB implements PasswordManagerPersistence {

    private final String dbPath;

    public PasswordManagerPersistenceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        String finalDBPath = dbPath.substring(0, dbPath.indexOf(".script"));
        return DriverManager.getConnection("jdbc:hsqldb:file:" + finalDBPath + ";shutdown=true", "SA", "");
    }
    @Override
    public List<PasswordManager> getPasswordManagerSequential(){
        final List<PasswordManager> passwords = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM PASSWORDS");
            while (rs.next()) {
                final PasswordManager passwordManager = fromResultSet(rs);
                passwords.add(passwordManager);
            }
            rs.close();
            st.close();

            return passwords;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }

    }


    private PasswordManager fromResultSet(final ResultSet rs) throws SQLException {
        final String username = rs.getString("USERNAME");
        final String website = rs.getString("WEBSITE");
        final String password = rs.getString("PASSWORD");
        final String webusername = rs.getString("WEBUSERNAME");


        return new PasswordManager(username, website, password, webusername);
    }

    @Override
    public PasswordManager insertPassword(PasswordManager currentPassword) {
        PasswordManager temp = checkDuplicate(currentPassword);

        if(temp == null){
            try (final Connection c = connection()) {
                final PreparedStatement st = c.prepareStatement("INSERT INTO passwords VALUES(?, ?, ?, ?)");
                st.setString(1, currentPassword.getUsername());
                st.setString(2, currentPassword.getWebsite());
                st.setString(3, currentPassword.getPassword());
                st.setString(4, currentPassword.getWebUsername());

                st.executeUpdate();
                temp = currentPassword;
            } catch (final SQLException e) {
                throw new Error("Nope, doesn't work bud!");
            }
        }
        else{
            temp = null;
        }

        return temp;
    }

    private PasswordManager checkDuplicate(PasswordManager currentUser) {
        PasswordManager p = null;
        List<PasswordManager> passwordsList = new ArrayList<>();
        passwordsList.addAll(getPasswordManagerSequential());

        String user = currentUser.getUsername();
        String webUsername = currentUser.getWebUsername();
        String websiteName = currentUser.getWebsite();
        String password = currentUser.getPassword();

        for(int i=0;i<passwordsList.size();i++){
            PasswordManager temp = passwordsList.get(i);
            if(temp.getUsername().equals(user) && temp.getWebsite().equals(websiteName) && temp.getWebUsername().equals(webUsername)
            && temp.getPassword().equals(password)){
                p = temp;
            }
        }
        return p;
    }

    @Override
    public PasswordManager deletePassword(PasswordManager currentPassword) {

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM passwords WHERE USERNAME = ? AND WEBSITE = ?");
            st.setString(1, currentPassword.getUsername());
            st.setString(2, currentPassword.getWebsite());

            st.executeUpdate();

            return currentPassword;
        } catch (final SQLException e) {
            throw new Error("Nope, doesn't work bud!");
        }
    }
}
