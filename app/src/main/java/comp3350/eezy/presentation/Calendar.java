package comp3350.eezy.presentation;

import static comp3350.eezy.presentation.HomePage.dark;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import comp3350.eezy.R;
import comp3350.eezy.application.AccessEvents;
import comp3350.eezy.application.AccessUsers;
import comp3350.eezy.objects.EezyUser;
import comp3350.eezy.objects.Event;

import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Calendar extends AppCompatActivity {
    private EditText eventName;
    private CalendarView calendarView;
    private String selectedDate;

    //NEW
    private AccessEvents accessEvents;
    private List<Event> eventList;
    String currUsername;

    private AccessUsers accessUsers;
    private List<EezyUser> userList;
    private EezyUser currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean darkMode = dark;

        //NEW - getting events list for this user
        accessEvents = new AccessEvents();
        eventList = new ArrayList<>();

        //NEW - Receive the username from last activity which was login page
        Intent intent = getIntent();
        currUsername = intent.getStringExtra("current");
        //get all the users
        accessUsers = new AccessUsers();
        userList = new ArrayList<>();

        try{
            userList.addAll(accessUsers.getUsers());
            eventList.addAll(accessEvents.getEvents());
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        //find our user using the username because it is unique and primary key
        currentUser = findUser(currUsername);

        if (darkMode) {
            setContentView(R.layout.activity_calendar_dark);
            calendarView = findViewById(R.id.calendar_view2);
        } else {
            setContentView(R.layout.activity_calendar);
            calendarView = findViewById(R.id.calendar_view);
        }
        eventName = findViewById(R.id.eventName);
        selectedDate = String.valueOf(calendarView.getDate());

        long l = calendarView.getDate();
        DateFormat d = new SimpleDateFormat("yyyyMMdd");
        Date res = new Date(l);
        String s = d.format(res);
        selectedDate = s;

        if(!eventList.isEmpty()){
            Event event = checkEventExists(selectedDate);

            if(event!=null){
                eventName.setText(event.getEventName());
            }
            else{
                eventName.setText("");
            }
        }

        Toast.makeText(Calendar.this, "Please select a date!", Toast.LENGTH_SHORT).show();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month = month+1;
                if(month<10 && dayOfMonth<10){
                    selectedDate = Integer.toString(year) + "0"+Integer.toString(month) + "0"+Integer.toString(dayOfMonth);
                }
                else if(month>=10 && dayOfMonth<10){
                    selectedDate = Integer.toString(year) + Integer.toString(month) + "0"+Integer.toString(dayOfMonth);
                }
                else if(month<10 && dayOfMonth>=10){
                    selectedDate = Integer.toString(year) + "0"+Integer.toString(month) + Integer.toString(dayOfMonth);
                }
                readEvent(view);
            }
        });
    }

    //whenever we delete any event for a user, we will call this method to get our list updated too
    private List<Event> updateEventList(){
        eventList.removeAll(eventList);
        eventList.addAll(accessEvents.getEvents());

        return eventList;
    }

    public void readEvent(View view) {

        Event event = checkEventExists(selectedDate);

        if(event!=null){
            eventName.setText(event.getEventName());
        }
        else{
            eventName.setText("");
        }
    }

    public void insertEvent(View view){
        if(!eventName.getText().toString().isEmpty()){

            Event event = new Event(currUsername, eventName.getText().toString(),selectedDate);
            Event temp = accessEvents.insertEvent(event);

            if(temp == null){
                //Update required, event already exists
                accessEvents.updateEvent(event);
                Toast.makeText(Calendar.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                eventName.setText(eventName.getText().toString());
            }
            else{
                Toast.makeText(Calendar.this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
            }
            eventList = updateEventList();



        }
        else{
            Toast.makeText(Calendar.this, "Event name cannot be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteEvent(View view){

        Event event = new Event(currUsername, eventName.getText().toString(), selectedDate);
        Event temp = accessEvents.deleteEvent(event);

        if(temp != null){
            eventName.setText("");
            Toast.makeText(Calendar.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
            eventList = updateEventList();
        }
        else{
            Toast.makeText(Calendar.this, "No event exists to delete!", Toast.LENGTH_SHORT).show();
        }
    }

    //finds our user with username
    private EezyUser findUser(String user){
        EezyUser curr = null;

        for(int i=0;i<userList.size();i++){
            EezyUser temp = userList.get(i);
            if(temp.getUsername().equals(user)){
                curr = temp;
            }
        }
        return curr;
    }

    //find event with the String Date to check if it exists or not for current user
    private Event checkEventExists(String date){
        Event temp = null;

        for(int i=0;i<eventList.size();i++){
            String userDate = eventList.get(i).getDate();
            String currDate = date;
            String user = eventList.get(i).getUsername();
            String currUser = currUsername;
            if(userDate.equals(currDate) && user.equals(currUser)){
                temp = eventList.get(i);
            }
        }
        return temp;
    }
}