package comp3350.eezy.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import comp3350.eezy.objects.Event;
import comp3350.eezy.persistence.CalendarPersistence;

public class CalendarPersistenceHSQLDB implements CalendarPersistence {

    private final String dbPath;

    public CalendarPersistenceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        String finalDBPath = dbPath.substring(0, dbPath.indexOf(".script"));
        return DriverManager.getConnection("jdbc:hsqldb:file:" + finalDBPath + ";shutdown=true", "SA", "");
    }

    @Override
    public List<Event> getEventsSequential() {
        final List<Event> events = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM events");

            while (rs.next()) {
                final Event event = fromResultSet(rs);
                events.add(event);
            }

            rs.close();
            st.close();

            return events;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private Event fromResultSet(final ResultSet rs) throws SQLException {
        final String username = rs.getString("USERNAME");
        final String eventName = rs.getString("EVENTNAME");
        final String date = rs.getString("DATE");
        return new Event(username, eventName, date);
    }

    @Override
    public Event insertEvent(Event currentEvent) {
        Event temp = checkDuplicate(currentEvent);

        if(temp == null){
            try (final Connection c = connection()) {
                final PreparedStatement st = c.prepareStatement("INSERT INTO events VALUES(?, ?, ?)");
                st.setString(1, currentEvent.getUsername());
                st.setString(2, currentEvent.getEventName());
                st.setString(3, currentEvent.getDate());

                st.executeUpdate();

                temp = currentEvent;
            } catch (final SQLException e) {
                throw new PersistenceException(e);
            }
        }
        else{
            temp = null;
        }
        return temp;
    }

    public Event checkDuplicate(Event event){
        Event e = null;
        List<Event> eventList = new ArrayList<>();
        eventList.addAll(getEventsSequential());

        String user = event.getUsername();
        String date = event.getDate();

        for(int i=0;i<eventList.size();i++){
            Event temp = eventList.get(i);
            if(temp.getUsername().equals(user) && temp.getDate().equals(date)){
                e = temp;
            }
        }
        return e;
    }

    @Override
    public Event deleteEvent(Event currentEvent) {
        Event temp = checkDuplicate(currentEvent);

        if(temp != null){
            try (final Connection c = connection()) {
                final PreparedStatement st = c.prepareStatement("DELETE FROM events WHERE USERNAME = ? AND DATE = ?");
                st.setString(1, currentEvent.getUsername());
                st.setString(2, currentEvent.getDate());

                st.executeUpdate();

            } catch (final SQLException e) {
                throw new Error("Nope, doesn't work bud!");
            }
        }
        else{
            temp = null;
        }
        return temp;
    }

    @Override
    public Event updateEvent(Event currentEvent) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE events SET EVENTNAME = ? WHERE USERNAME = ? AND DATE = ?");
            st.setString(1, currentEvent.getEventName());
            st.setString(2, currentEvent.getUsername());
            st.setString(3, currentEvent.getDate());

            st.executeUpdate();

            return currentEvent;
        } catch (final SQLException e) {
            throw new Error("Nope, doesn't work bud!");
        }
    }
}
