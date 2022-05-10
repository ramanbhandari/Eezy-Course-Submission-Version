package comp3350.eezy.persistence;

import java.util.List;
import comp3350.eezy.objects.Event;

public interface CalendarPersistence {

    List<Event> getEventsSequential();
    Event insertEvent(Event currentEvent);
    Event deleteEvent(Event currentEvent);
    Event updateEvent(Event currentEvent);
    Event checkDuplicate(Event currentEvent);
}
