package com.example.tutors.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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
        Button btn = findViewById(R.id.btnCreateLesson);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), StudentListActivity.class);
            startActivity(intent);
        });
    }

    private void setupListView() {
        ListView listView = findViewById(R.id.listViewTutorLesson);
        String currentUserId = FirebaseHelper.getIdCurrentUser();
        TutorLessonAdapter studentLessonAdapter = new TutorLessonAdapter(this, FirebaseHelper.getLessonByTutorId(currentUserId));
        listView.setAdapter(studentLessonAdapter);
    }
}