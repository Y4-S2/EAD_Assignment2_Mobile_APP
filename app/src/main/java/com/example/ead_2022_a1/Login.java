package com.example.ead_2022_a1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    //declare variables
    Button loginBtn;
    Button signUpBtn;
    TextInputEditText username, password;
    TextInputLayout usernameLayout, passwordLayout;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialize variables
        loginBtn = findViewById(R.id.login);
        signUpBtn = findViewById(R.id.lregister);
        username = findViewById(R.id.login_username_edittext);
        password = findViewById(R.id.login_password_edittext);
        usernameLayout = findViewById(R.id.login_username_layout);
        passwordLayout = findViewById(R.id.login_password_layout);
        DB = new DBHelper(this);

        //sign up button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        //user login
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get values from edit text
                String user = usernameLayout.getEditText().getText().toString();
                String pass = passwordLayout.getEditText().getText().toString();

                //check if the fields are empty
                if(user.equals("")||pass.equals(""))
                    //if empty show error
                    Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUserPass = DB.checkUsernamePassword(user, pass);
                    //check if the username and password are correct
                    if(checkUserPass==true){
                        Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, SelectFuelType.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}