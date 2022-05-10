package comp3350.eezy.presentation;

import static comp3350.eezy.presentation.HomePage.dark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import comp3350.eezy.R;
import comp3350.eezy.application.AccessPasswordManager;
import comp3350.eezy.objects.PasswordManager;

public class NewPassword extends AppCompatActivity{
    String currUsername;
    private AccessPasswordManager accessPasswordManager;
    private List<PasswordManager> passwordsList;
    EditText websiteName, websiteUsername, websitePassword;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean darkMode = dark;

        //Receive the username from last activity which was login page
        Intent intent = getIntent();
        currUsername = intent.getStringExtra("current");

        accessPasswordManager = new AccessPasswordManager();
        passwordsList = new ArrayList<>();

        try{
            passwordsList.addAll(accessPasswordManager.getPasswords());
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }

        if (darkMode) {
            setContentView(R.layout.activity_new_password_dark);
        } else {
            setContentView(R.layout.activity_new_password);
        }

        websiteName = findViewById(R.id.enterUser);
        websiteUsername = findViewById(R.id.enterName);
        websitePassword= findViewById(R.id.enterPass);
    }

    public void insertPassword(View view){
        //NEW
        if(websiteName.getText().toString().isEmpty() || websiteUsername.getText().toString().isEmpty()
                || websitePassword.getText().toString().isEmpty()){
            Toast.makeText(NewPassword.this, "Fields cannot be empty!", Toast.LENGTH_LONG).show();
        }
        else {
            PasswordManager currPassword = new PasswordManager(currUsername,websiteName.getText().toString(), websitePassword.getText().toString(), websiteUsername.getText().toString());
            PasswordManager temp = accessPasswordManager.insertPassword(currPassword);
            if(temp == null){
                Toast.makeText(NewPassword.this, "Same username and Passwords exists for this Website!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(NewPassword.this, "Password successfully added!", Toast.LENGTH_LONG).show();
            }

        }
    }
}