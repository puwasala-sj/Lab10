package com.example.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DBHelper;
import Database.UserMaster;

public class TeacherActivity extends AppCompatActivity {
    DBHelper db;
    EditText username, Subject, Message;
    Button send;
    String receivedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        Intent receivedIntent = getIntent();

        username = (EditText)findViewById(R.id.username);
        receivedUser = receivedIntent.getStringExtra("username");
        username.setText(receivedUser);

        db = new DBHelper(this);
        Subject = (EditText)findViewById(R.id.subject);
        Message = (EditText)findViewById(R.id.message);
        send = (Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String sub = Subject.getText().toString();
                String mess = Message.getText().toString();
                boolean res = db.addInfoMessage(user,sub,mess);
                if(res == true){
                    Toast.makeText(TeacherActivity.this,"Successfully message added",Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(TeacherActivity.this,StudentActivity.class);
                    startActivity(login);
                }
                else{
                    Toast.makeText(TeacherActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
