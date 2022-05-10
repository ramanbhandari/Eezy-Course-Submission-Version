package comp3350.eezy.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import comp3350.eezy.business.Main;
import comp3350.eezy.R;
import comp3350.eezy.application.AccessUsers;
import comp3350.eezy.objects.EezyUser;
import comp3350.eezy.persistence.CalendarDB;

public class LoginPage extends AppCompatActivity {
    private CalendarDB db;
    Button loginButton;
    EditText user, pass;

    private AccessUsers accessUsers;
    private List<EezyUser> userList;

    EezyUser currentUser = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NEW
        copyDatabaseToDevice();
        accessUsers = new AccessUsers();
        userList = new ArrayList<>();

        try{
            userList.addAll(accessUsers.getUsers());
        }
        catch(Exception e){
            throw new RuntimeException("Some error occurred in copying the users to user list!");
        }
        login();
        redirectSignup();
    }

    public void redirectSignup() {
        TextView signupText = (TextView) findViewById(R.id.signupText);
        String text = "New to Eezy? Click Signup!";
        SpannableString ss = new SpannableString(text);
        signupText.setTextColor(Color.parseColor("#F7A9A8"));
    }

    public void onClickSignup(View view){
        Intent intent = new Intent(LoginPage.this, SignupPage.class);
        LoginPage.this.startActivity(intent);
    }

    public void login() {
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String userString = user.getText().toString();
                String passString = pass.getText().toString();
                if((currentUser = checkDuplicate(userString, passString)) != null){
                    loginSuccessful(userString);
                }
                else{
                    loginFailure(userString, passString);
                }
            }
        });
    }

    public EezyUser checkDuplicate(String user, String pass){
        EezyUser curr = null;

        for(int i=0;i<userList.size();i++) {
            EezyUser temp = userList.get(i);
            if (temp.getUsername().equals(user) && temp.getPassword().equals(pass)) {
                curr = temp;
            }
        }
        return curr;
    }


    public void loginSuccessful(String userString){
        Toast.makeText(LoginPage.this, "Login Successful!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginPage.this, HomePage.class);
        intent.putExtra("current", userString);
        startActivity(intent);
    }

    public void loginFailure(String user, String pass){
        if(!user.isEmpty() && !pass.isEmpty()) {
            Toast.makeText(LoginPage.this, "Username and/or Password is incorrect!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(LoginPage.this, "Username and/or Password cannot be empty!", Toast.LENGTH_LONG).show();
        }
    }

    //Copy the database to the data file of the device
    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            throw new RuntimeException("Some error occurred in copying the database!");
        }
    }
    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}
