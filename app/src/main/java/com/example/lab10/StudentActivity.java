package com.example.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Database.DBHelper;

public class StudentActivity extends AppCompatActivity {
    //private static final String TAG = "ListMessage";
    DBHelper db;

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        listView = (ListView)findViewById(R.id.ListView);
        db = new DBHelper(this);
        listMessage();
    }

    private void listMessage() {
        //Log.d(TAG,"ListMessage: Displaying data in the ListView");
        final Cursor message = db.getAllmessage();
        ArrayList<String> listmessage = new ArrayList<>();
        while(message.moveToNext()){
            listmessage.add(message.getString(2));
            listmessage.add(message.getString(3));
        }

        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listmessage);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String subject = adapterView.getItemAtPosition(i).toString();
                String message = adapterView.getItemAtPosition(i).toString();

                Cursor data = db.getmessageID(subject); //get the id associated with that name
                int id = -1;
                while(data.moveToNext()){
                    id = data.getInt(0);
                }
                if(id > -1){

                    Intent editScreenIntent = new Intent(StudentActivity.this,MessageView.class);
                    editScreenIntent.putExtra("id",id);
                    editScreenIntent.putExtra("subject",subject);
                    editScreenIntent.putExtra("Message",message);
                    startActivity(editScreenIntent);
                }
                else{
                    Toast.makeText(StudentActivity.this, "No ID found", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
