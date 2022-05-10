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
import comp3350.eezy.persistence.UserPersistence;

public class UserPersistenceHSQLDB implements UserPersistence {

    private final String dbPath;

    public UserPersistenceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        String finalDBPath = dbPath.substring(0, dbPath.indexOf(".script"));
        return DriverManager.getConnection("jdbc:hsqldb:file:" + finalDBPath + ";shutdown=true", "SA", "");
    }

    @Override
    public List<EezyUser> getUserSequential() {
        final List<EezyUser> users = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM USERS");
            while (rs.next()) {
                final EezyUser eezyUser = fromResultSet(rs);
                users.add(eezyUser);
            }
            rs.close();
            st.close();

            return users;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private EezyUser fromResultSet(final ResultSet rs) throws SQLException {
        final String name = rs.getString("NAME");
        final String username = rs.getString("USERNAME");
        final String email = rs.getString("EMAIL");
        final String password = rs.getString("PASSWORD");

        return new EezyUser(name, username, email, password);
    }

    @Override
    public EezyUser insertUser(EezyUser currentUser) {
        EezyUser temp = checkDuplicate(currentUser);

        if(temp == null){
            try (final Connection c = connection()) {
                final PreparedStatement st = c.prepareStatement("INSERT INTO USERS VALUES(?, ?, ?, ?)");
                st.setString(1, currentUser.getUsername());
                st.setString(2, currentUser.getName());
                st.setString(3, currentUser.getEmail());
                st.setString(4, currentUser.getPassword());

                st.executeUpdate();
                temp = currentUser;
            } catch (final SQLException e) {
                throw new PersistenceException(e);
            }
        }
        else{
            temp = null;
        }
        //returns null if duplicate email and username user already exists
        return temp;
    }

    @Override
    public EezyUser checkDuplicate(EezyUser currentUser) {
        EezyUser e = null;
        List<EezyUser> userList = new ArrayList<>();
        userList.addAll(getUserSequential());

        String user = currentUser.getUsername();
        String email = currentUser.getEmail();

        for(int i=0;i<userList.size();i++){
            EezyUser temp = userList.get(i);
            if(temp.getUsername().equals(user) && temp.getEmail().equals(email)){
                e = temp;
            }
        }
        return e;
    }

    @Override
    public void deleteUser(EezyUser currentUser) {

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM USERS WHERE USERNAME = ?");
            st.setString(1, currentUser.getUsername());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
