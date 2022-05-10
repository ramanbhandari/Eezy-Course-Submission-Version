package comp3350.eezy.application;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import comp3350.eezy.objects.Event;
import comp3350.eezy.persistence.hsqldb.CalendarPersistenceHSQLDB;
import comp3350.eezy.utils.TestUtils;

public class AccessEventsIT extends TestCase {
    private AccessEvents accessEvents;
    private Event event0;
    private Event event1;
    private Event event2;

    @Before
    public void setUp() throws IOException
    {
        accessEvents = new AccessEvents(new CalendarPersistenceHSQLDB(TestUtils.getDB().getAbsolutePath()));
        event0 = new Event("Android", "Test event 1", "01012022");
        event1 = new Event("iOS", "Test event 2", "25032022");
        event2 = new Event("Linux", "Test event 2", "15122021");
    }

    @Test
    public void testAccessEventsNotNull(){assertNotNull(accessEvents);}

    @Test
    public void testAccessEventsInsert()
    {
        Event event0Inserted = accessEvents.insertEvent(event0);
        assertSame(event0Inserted, event0);

        Event event1Inserted = accessEvents.insertEvent(event1);
        assertSame(event1Inserted, event1);

        Event event2Inserted = accessEvents.insertEvent(event2);
        assertSame(event2Inserted, event2);
    }

    @Test
    public void testAccessEventsDelete()
    {
        //list before any insertions
        List<Event> eventList = accessEvents.getEvents();
        assertFalse(eventList.contains(event0));

        accessEvents.insertEvent(event0);
        accessEvents.insertEvent(event1);

        //list after adding some events
        eventList = accessEvents.getEvents();
        assertTrue(eventList.contains(event0));
        assertTrue(eventList.contains(event1));

        accessEvents.deleteEvent(event0);
        accessEvents.deleteEvent(event1);
        accessEvents.insertEvent(event2);

        //list after deleting and adding some events
        eventList = accessEvents.getEvents();
        assertFalse(eventList.contains(event0));
        assertFalse(eventList.contains(event1));
        assertTrue(eventList.contains(event2));
    }

    @Test
    public void testAccessEventsGetEvents()
    {
        Event testEvent = new Event("DP", "This is an internal test", "01012020");
        accessEvents.insertEvent(event0);
        accessEvents.insertEvent(event1);
        accessEvents.insertEvent(event2);
        accessEvents.insertEvent(testEvent);

        List<Event> events = accessEvents.getEvents();
        assertTrue(events.contains(event0));
        assertTrue(events.contains(event1));
        assertTrue(events.contains(event2));
        assertTrue(events.contains(testEvent));
    }

    @Test
    public void testAccessEventsSequential()
    {
        List<Event> events = accessEvents.getEvents();
        for(Event event : events)
            assertSame(accessEvents.getSequential(), event);
    }

    @Test
    public void testAccessEventsDuplicate()
    {
        //empty event list
        assertNull(accessEvents.checkDuplicateEvent(event0));

        //inserting events
        assertNotNull(accessEvents.insertEvent(event0));
        assertNotNull(accessEvents.insertEvent(event1));
        assertNotNull(accessEvents.insertEvent(event2));

        //checking some duplicate events
        assertNotNull(accessEvents.checkDuplicateEvent(event0));
        assertNotNull(accessEvents.checkDuplicateEvent(event2));
    }
}