package comp3350.eezy.application;

import java.util.ArrayList;
import java.util.List;
import comp3350.eezy.business.Services;
import comp3350.eezy.objects.Event;
import comp3350.eezy.persistence.CalendarPersistence;

public class AccessEvents {
    private CalendarPersistence calendarPersistence;
    private List<Event> events;
    private Event event;
    private int currentEvent;

    public AccessEvents(){
        calendarPersistence = Services.getCalendarPersistence();
        events = null;
        event = null;
        currentEvent = 0;
    }

    public AccessEvents(final CalendarPersistence calendarPersistence){
        this();
        this.calendarPersistence = calendarPersistence;
    }

    public List<Event> getEvents(){
        events = calendarPersistence.getEventsSequential();
        List<Event> theEvents = new ArrayList<>(events);
        return theEvents;
    }

    public Event getSequential()
    {
        if(events == null)
        {
            events = calendarPersistence.getEventsSequential();
            currentEvent = 0;
        }
        if(currentEvent < events.size())
        {
            event = (Event) events.get(currentEvent);
            currentEvent++;
        }
        else
        {
            events = null;
            event = null;
            currentEvent = 0;
        }
        return event;
    }

    public Event insertEvent(Event currentEvent){
        return calendarPersistence.insertEvent(currentEvent);
    }

    public Event deleteEvent(Event currentEvent){
        return calendarPersistence.deleteEvent(currentEvent);
    }

    public Event updateEvent(Event currentEvent){
        return calendarPersistence.updateEvent(currentEvent);
    }

    public Event checkDuplicateEvent(Event currentEvent)
    {
        return  calendarPersistence.checkDuplicate(currentEvent);
    }
}
