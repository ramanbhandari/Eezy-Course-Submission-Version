package comp3350.eezy.presentation;

import static comp3350.eezy.presentation.HomePage.dark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.eezy.R;
import comp3350.eezy.application.AccessPasswordManager;
import comp3350.eezy.objects.PasswordManager;

public class ViewPasswords extends AppCompatActivity {
    //NEW
    private AccessPasswordManager accessPasswordManager;
    private List<PasswordManager> passwordsList;
    private ArrayAdapter<PasswordManager> passwordsArrayAdapter;
    private String currUsername;
    private int currentPasswordIndex = -1;

    private PasswordManager selected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean darkMode = dark;

        if (darkMode) {
            setContentView(R.layout.activity_view_passwords_dark);
        } else {
            setContentView(R.layout.activity_view_passwords);
        }

        Intent intent = getIntent();
        currUsername = intent.getStringExtra("current");

        accessPasswordManager = new AccessPasswordManager();
        passwordsList = new ArrayList<>();

        //prints the passwords basically for the current user
        ListView l = getAdapterSet();
        //sets the adapter for onclick to the date and store that date
        getAdapterSelect(l);
    }

    private ListView getAdapterSet(){
        final ListView listView;
        try {
            passwordsList.addAll(accessPasswordManager.getPasswords());
            passwordsList = keepCurrentPasswords(passwordsList);

            passwordsArrayAdapter = new ArrayAdapter<PasswordManager>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, passwordsList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    text1.setTextColor(Color.parseColor("#F7A9A8"));
                    text1.setBackgroundColor(Color.parseColor("#613F75"));
                    text1.setText("Website: " + passwordsList.get(position).getWebsite() + " , Username: " + passwordsList.get(position).getWebUsername() + " , Passwords: " + passwordsList.get(position).getPassword());

                    return view;
                }
            };

            listView = (ListView) findViewById(R.id.listCourses);
            listView.setAdapter(passwordsArrayAdapter);
        }
        catch(Exception e){
            throw new RuntimeException("Some error occurred in copying the password list!");
        }
        return listView;
    }
    public void getAdapterSelect(ListView listView){
        try {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position == currentPasswordIndex) {
                        currentPasswordIndex= -1;
                    } else {
                        currentPasswordIndex = position;
                        selectedPassword(position);
                    }
                }
            });
        }
        catch(Exception e){
            throw new RuntimeException("Some error occurred in copying the password list!");
        }
    }

    private void selectedPassword(int position){
        selected = passwordsArrayAdapter.getItem(position);
    }

    //keeps only the current user's passwords and returns the list
    private List<PasswordManager> keepCurrentPasswords(List<PasswordManager>passwordsList){
        List<PasswordManager> temp = new ArrayList<>();

        for(int i =0;i<passwordsList.size();i++){
            if(passwordsList.get(i).getUsername().equals(currUsername)){
                temp.add(passwordsList.get(i));
            }
        }
        return temp;
    }

    public void  deleteSelectedPassword(View view){
        if(selected != null){
            accessPasswordManager.deletePassword(selected);
            passwordsArrayAdapter.remove(selected);
            passwordsArrayAdapter.notifyDataSetChanged();
            Toast.makeText(ViewPasswords.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ViewPasswords.this, "Please select a password to delete!", Toast.LENGTH_SHORT).show();
        }
    }
}