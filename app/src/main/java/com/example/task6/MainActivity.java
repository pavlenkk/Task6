package com.example.task6;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.app.AlarmManager;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.PendingIntent;
import android.content.Context;




public class MainActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextMessage;
    private Button buttonSetDateTime, buttonSave, buttonViewReminders;
    private TextView textViewDateTime;
    private DatabaseHelper databaseHelper;
    private int hour, minute, year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSetDateTime = findViewById(R.id.buttonSetDateTime);
        textViewDateTime = findViewById(R.id.textViewDateTime);
        buttonSave = findViewById(R.id.buttonSave);
        buttonViewReminders = findViewById(R.id.buttonViewReminders);

        databaseHelper = new DatabaseHelper(this);

        buttonSetDateTime.setOnClickListener(v -> showDateTimePicker());
        buttonSave.setOnClickListener(v -> saveReminder());
        buttonViewReminders.setOnClickListener(v -> viewReminders());
    }

    private void showDateTimePicker() {
        // Используем DatePicker для выбора даты
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
            textViewDateTime.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        }, year, month, day);
        datePickerDialog.show();

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minuteOfHour) -> {
            textViewDateTime.append(" " + hourOfDay + ":" + String.format("%02d", minuteOfHour));
        }, hour, minute, true);
        timePickerDialog.show();
    }

    private void saveReminder() {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();
        String dateTime = textViewDateTime.getText().toString();

        if (title.isEmpty() && message.isEmpty() && dateTime.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isInserted = databaseHelper.addReminder(title, message, dateTime);
        if (isInserted) {
            Toast.makeText(this, "Напоминание сохранено", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Ошибка сохранения напоминания", Toast.LENGTH_SHORT).show();
        }

    }
    private void setReminder(long timeInMillis, String title, String message) {
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("TITLE", title);
        intent.putExtra("MESSAGE", message);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
    }


    private void viewReminders() {
        // Реализуйте переход к активности для просмотра напоминаний
        Intent intent = new Intent(this, ViewRemindersActivity.class);
        startActivity(intent);
    }
}