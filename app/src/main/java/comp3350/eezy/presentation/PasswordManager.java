package comp3350.eezy.presentation;

import static comp3350.eezy.presentation.HomePage.dark;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import comp3350.eezy.R;

public class PasswordManager extends AppCompatActivity {
    String currUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean darkMode = dark;

        //NEW - Receive the username from last activity which was Homepage page
        Intent intent = getIntent();
        currUsername = intent.getStringExtra("current");


        if (darkMode) {
            setContentView(R.layout.activity_password_manager_dark);
        } else {
            setContentView(R.layout.activity_password_manager);
        }

        Button goToAdd = findViewById(R.id.buttonAdd);

        goToAdd.setOnClickListener(view -> {
            Intent in = new Intent(PasswordManager.this, NewPassword.class);
            in.putExtra("current", currUsername);//Sending the username along to next activity
            startActivity(in);
        });

        Button b = findViewById(R.id.buttonManage);

        b.setOnClickListener(view -> {
            Intent in = new Intent(PasswordManager.this, ViewPasswords.class);
            in.putExtra("current", currUsername);//Sending the username along to next activity
            startActivity(in);
        });
    }
}