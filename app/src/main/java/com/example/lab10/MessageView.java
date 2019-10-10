package com.example.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import Database.DBHelper;

public class MessageView extends AppCompatActivity {
    DBHelper db;
    EditText sub,mess;
    int Id;
    String subject,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);

        db = new DBHelper(this);
        sub = (EditText)findViewById(R.id.subject);
        mess = (EditText)findViewById(R.id.message);

        Intent receivedIntent = getIntent();

        Id = receivedIntent.getIntExtra("id",-1);
        subject = receivedIntent.getStringExtra("subject");
        sub.setText(subject);
        message = receivedIntent.getStringExtra("Message");
        mess.setText(message);
    }
}
