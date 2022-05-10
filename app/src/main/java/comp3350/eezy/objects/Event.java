package comp3350.eezy.objects;

public class Event{
    private String eventName;
    private String date;
    private String username;

    public Event(String username, String eventName, String date){
        this.eventName = eventName;
        this.date = date;
        this.username = username;
    }
    public String getEventName(){
        return this.eventName;
    }

    public String getDate(){ return this.date; }

    public String getUsername(){
        return this.username;
    }

    public boolean equals(Object other)
    {
        boolean result = false;

        if(other != null) {
            if (other instanceof Event) {
                Event event = (Event) other;
                result = this.getUsername().equals(event.getUsername())
                        && this.getEventName().equals(event.getEventName())
                        && this.getDate().equals(event.getDate());
            }
        }
        return result;
    }
}
