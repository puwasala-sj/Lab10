package com.example.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import Database.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    Button register;
    EditText username,password;
    CheckBox type;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DBHelper(this);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        type  = (CheckBox)findViewById(R.id.teacher);
        type = (CheckBox)findViewById(R.id.student);

        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String ty = type.getText().toString();

                boolean res = db.addInfoUser(user,pass,ty);
                if(res == true){
                    Toast.makeText(RegisterActivity.this,"Successfully registered",Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(login);
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
