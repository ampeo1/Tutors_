package com.example.tutors.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.Models.Lesson;
import com.example.tutors.Models.Tutor;
import com.example.tutors.Models.TutorsStudent;
import com.example.tutors.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.UUID;

public class BookLeassonActivity extends AppCompatActivity {
    private String currentStudentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_leasson);
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null) {
            currentStudentId = arguments.getString(TutorsStudent.class.getSimpleName());
        }

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        datePicker.setMinDate((new Date()).getTime());
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        EditText editTopic = (EditText) findViewById(R.id.editText);
        Spinner spinner = findViewById(R.id.addLessonItemsSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.items_array_rus, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button btn = (Button)findViewById(R.id.btnBookDate);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                int selectedId = (int)spinner.getSelectedItemId();
                FirebaseHelper.addLesson(buildLesson(ItemsTypes.values()[selectedId]));

                Intent intent = new Intent(getApplicationContext(), StudentListActivity.class);
                startActivity(intent);
            }

            //TODO вместо стринги => FirebaseHelper.getIdCurrentUser()
            @RequiresApi(api = Build.VERSION_CODES.M)
            private Lesson buildLesson(ItemsTypes types){
                Date date = new Date();
                date.setYear(datePicker.getYear());
                date.setMonth(datePicker.getMonth());
                date.setDate(datePicker.getDayOfMonth());
                date.setHours(timePicker.getHour());
                date.setMinutes(timePicker.getMinute());
                return new Lesson(editTopic.getText().toString(), "BnqNvVGOu9fPmDJ0OGoTl09eqBA3", currentStudentId, date, types);
            }
        });
    }
}