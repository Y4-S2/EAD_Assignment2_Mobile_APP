package com.example.ead_2022_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {

    //declare variables
    TextInputEditText username, password, confirmPassword;
    Button register_button , rLogin;
    TextInputLayout usernameLayout, passwordLayout, confirmPasswordLayout;
    RadioButton userType;
    RadioGroup radioGroup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialize variables
        username = findViewById(R.id.register_username_edittext);
        password = findViewById(R.id.register_password_edittext);
        confirmPassword = findViewById(R.id.register_confirm_password_edittext);
        register_button = findViewById(R.id.register_button);
        rLogin = findViewById(R.id.rLogin);
        usernameLayout = findViewById(R.id.register_username_layout);
        passwordLayout = findViewById(R.id.register_password_layout);
        confirmPasswordLayout = findViewById(R.id.register_confirm_password_layout);
        radioGroup = findViewById(R.id.radioGroup);


        //user registration
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get values from edit text
                String user = usernameLayout.getEditText().getText().toString();
                String pass = passwordLayout.getEditText().getText().toString();
                String cPass = confirmPasswordLayout.getEditText().getText().toString();
                DB = new DBHelper(Register.this);

                //usertype
                int selectedId = radioGroup.getCheckedRadioButtonId();
                userType = findViewById(selectedId);
                String type = userType.getText().toString();

                //check if the fields are empty
                if(user.equals("")||pass.equals("")||cPass.equals(""))
                    //if empty show error
                    Toast.makeText(Register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    //check if the password and confirm password are same
                    if(pass.equals(cPass)){
                        Boolean checkUser = DB.checkUsernamePassword(user, pass);
                        //check if the username already exists
                        if(checkUser==false){
                            Boolean insert = DB.insert(user, pass , type);
                            //check if the data is inserted
                            if(insert==true){
                                Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                                if(type.equals("User")){
                                    Intent intent1 = new Intent(getApplicationContext(),AddVehicle.class);
                                    intent1.putExtra("userName",user);
                                    startActivity(intent1);
                                }
                                else if(type.equals("Station Owner")){
                                    Intent intent2 = new Intent(getApplicationContext(),Login.class);
                                    intent2.putExtra("userName",user);
                                    startActivity(intent2);
                                }
                            }else{
                                //if not inserted show error
                                Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            //if username already exists show error
                            Toast.makeText(Register.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        //if password and confirm password are not same show error
                        Toast.makeText(Register.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}