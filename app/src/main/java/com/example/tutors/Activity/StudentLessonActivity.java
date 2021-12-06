package com.example.tutors.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tutors.Adapters.SearchTutorsAdapter;
import com.example.tutors.Adapters.StudentLessonAdapter;
import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.Tutor;
import com.example.tutors.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class StudentLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_lesson);
        setupListView();
    }

    private void setupListView() {
        ListView listView = findViewById(R.id.listViewStudentLesson);
        StudentLessonAdapter studentLessonAdapter = new StudentLessonAdapter(this, FirebaseHelper.getLessonByStudentId("vJj67QeY4ARWConJwap0wuV8Pgv2"));
        listView.setAdapter(studentLessonAdapter);
    }
}