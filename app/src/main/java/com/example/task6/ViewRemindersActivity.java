package com.example.task6;

import android.os.Bundle;

import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;

public class ViewRemindersActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper databaseHelper;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reminders);

        listView = findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);
        displayReminders();
    }

    private void displayReminders() {
        Cursor cursor = databaseHelper.getAllReminders();
        ArrayList<String> remindersList = new ArrayList<>();

        while (cursor.moveToNext()) {
            String title = cursor.getString(1);
            String message = cursor.getString(2);
            String dateTime = cursor.getString(3);
            remindersList.add(title + "\n" + message + "\n" + dateTime);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, remindersList);
        listView.setAdapter(adapter);
    }
}