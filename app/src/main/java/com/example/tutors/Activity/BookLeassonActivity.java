package com.example.tutors.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.Models.Lesson;
import com.example.tutors.Models.Tutor;
import com.example.tutors.R;

import java.util.Date;
import java.util.UUID;

public class BookLeassonActivity extends AppCompatActivity {
    private Tutor tutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_leasson);
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null) {
            tutor = (Tutor) arguments.getSerializable(Tutor.class.getSimpleName());
        }

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        datePicker.setMinDate((new Date()).getTime());
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        EditText editTopic = (EditText) findViewById(R.id.editText);

        Button btn = (Button)findViewById(R.id.btnBookDate);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                FirebaseHelper.addLesson(buildLesson());

                Intent intent = new Intent(getApplicationContext(), StudentLessonActivity.class);
                startActivity(intent);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            private Lesson buildLesson(){
                Date date = new Date();
                date.setYear(datePicker.getYear());
                date.setMonth(datePicker.getMonth());
                date.setDate(datePicker.getDayOfMonth());
                date.setHours(timePicker.getHour());
                date.setMinutes(timePicker.getMinute());
                return new Lesson(editTopic.getText().toString(), tutor.getId(), FirebaseHelper.getIdCurrentUser(), date, ItemsTypes.MATHS);
            }
        });
    }
}