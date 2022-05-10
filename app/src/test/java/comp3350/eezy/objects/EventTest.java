package comp3350.eezy.objects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import comp3350.eezy.application.AccessEvents;
import comp3350.eezy.persistence.CalendarPersistence;

public class EventTest extends TestCase {

    CalendarPersistence events;
    AccessEvents event;

    @Before
    public void setUp()
    {
        events = mock(CalendarPersistence.class);
        event = new AccessEvents(events);
    }

    @Test
    public void testNotNull()
    {
        assertNotNull(event);
    }

    @Test
    public void testEventGetters()
    {
        Event e1 = new Event("ezypzy","just a test", "01012020");
        assertNotNull(e1);
        assertSame("ezypzy", e1.getUsername());
        assertSame("just a test", e1.getEventName());
        assertSame("01012020", e1.getDate());

        Event e2 = new Event("hello", null, "01012001");
        assertSame(null, e2.getEventName());
        assertNotSame("ezypzy", e2.getUsername());

        Event newEvent = new Event("eezy", "itr 3 due", "20220425");
        final ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(newEvent);
        when(events.getEventsSequential()).thenReturn(eventList);
        assertEquals(events.getEventsSequential().size(), 1);
        verify(events).getEventsSequential();
    }

    @Test
    public void testEventEquals()
    {

        Event newEvent1 = new Event("remmy", "deadline", "20220425");
        Event newEvent2 = new Event("remmy", "deadline", "20220425");
        boolean check = newEvent1.equals(newEvent2);
        final ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(newEvent1);
        when(events.getEventsSequential()).thenReturn(eventList);
        assertEquals(events.getEventsSequential().contains(newEvent1), true);
        assertEquals(check, true);
        verify(events).getEventsSequential();

        Event e1 = new Event("remmy", "abhi", "23");
        Event e2 = new Event("dhairya", "winson", "24");
        assertFalse(e1.equals(e2));

        e1 = new Event("abhi", "kunal", "01012020");
        e2 = new Event("abhi", "kunal", "01022020");
        assertFalse(e1.equals(e2));

        e1 = new Event("hello", "testEvent", "01012001");
        e2 = new Event("hello", "testEvent", "01012001");
        assertTrue(e1.equals(e2));
    }
}
