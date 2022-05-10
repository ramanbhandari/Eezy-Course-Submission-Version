package comp3350.eezy.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import comp3350.eezy.R;
import comp3350.eezy.application.AccessUsers;
import comp3350.eezy.objects.EezyUser;

public class SignupPage extends AppCompatActivity {
    EditText name, email, username, password, confirmPassword;

    private AccessUsers accessUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        accessUsers = new AccessUsers();
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        confirmPassword = findViewById(R.id.confirm_password);
    }

    //This method is called on click of the Signup button after filling all the fields
    public void insertUser(View view){
        if(username.getText().toString().isEmpty() || name.getText().toString().isEmpty()
                || email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty()){
            Toast.makeText(SignupPage.this, "Fields cannot be empty!", Toast.LENGTH_LONG).show();
        }
        else if(!(password.getText().toString()).equals(confirmPassword.getText().toString())){
            Toast.makeText(SignupPage.this, "Passwords don't match, Try again!", Toast.LENGTH_LONG).show();
        }
        else {
            EezyUser currUser = new EezyUser(name.getText().toString(), username.getText().toString(), email.getText().toString(), password.getText().toString());
            EezyUser temp = accessUsers.insertUser(currUser);
            if(temp == null){
                Toast.makeText(SignupPage.this, "Username and/or email already exists, Try again!", Toast.LENGTH_LONG).show();
            }
            else if(temp != null){
                Toast.makeText(SignupPage.this, "Successfully signed up!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignupPage.this, LoginPage.class);
                this.startActivity(intent);
            }
        }
    }
}
