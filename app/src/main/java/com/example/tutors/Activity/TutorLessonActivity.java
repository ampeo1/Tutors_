package com.example.tutors.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.tutors.Adapters.StudentLessonAdapter;
import com.example.tutors.Adapters.TutorLessonAdapter;
import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.R;

public class TutorLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_lesson);
        setupListView();
    }

    private void setupListView() {
        ListView listView = findViewById(R.id.listViewTutorLesson);
        TutorLessonAdapter studentLessonAdapter = new TutorLessonAdapter(this, FirebaseHelper.getLessonByTutorId("79b036ef-af85-485f-bbac-cb68fbe71a14"));
        listView.setAdapter(studentLessonAdapter);
    }
}