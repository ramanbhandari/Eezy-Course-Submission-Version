package comp3350.eezy.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import comp3350.eezy.R;

public class HomePage extends AppCompatActivity{
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch darkMode;
    private ConstraintLayout mainLayout;
    public static boolean dark;

    String currUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        darkMode = findViewById(R.id.darkSwitch);
        mainLayout = findViewById(R.id.layout);

        Intent intent = getIntent();
        currUsername = intent.getStringExtra("current");

        darkMode.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                dark = true;
                mainLayout.setBackgroundColor(Color.parseColor("#150E1A"));
                darkMode.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                dark = false;
                mainLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                darkMode.setTextColor(Color.parseColor("#000000"));
            }
        });
        mainLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        darkMode.setTextColor(Color.parseColor("#000000"));

    }

    public void onClickCalendar(View view){
        Intent in = new Intent(HomePage.this, Calendar.class);
        in.putExtra("current", currUsername);//Sending the username along to next activity
        startActivity(in);
    }

    public void onClickPassword(View view){
        Intent in = new Intent(HomePage.this, PasswordManager.class);
        in.putExtra("current", currUsername);//Sending the username along to next activity
        startActivity(in);
    }
}
