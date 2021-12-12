package com.example.tutors.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tutors.Adapters.StudentAdapter;
import com.example.tutors.Adapters.TutorLessonAdapter;
import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.Tutor;
import com.example.tutors.Models.TutorsStudent;
import com.example.tutors.R;

public class StudentListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        setupListView();
    }

    private void setupListView() {
        ListView listView = findViewById(R.id.listViewTutorsStudent);
        //TODO
        /*        String currentUserId = FirebaseHelper.getIdCurrentUser();*/
        String currentUserId = "BnqNvVGOu9fPmDJ0OGoTl09eqBA3";
        StudentAdapter studentAdapter = new StudentAdapter(this, FirebaseHelper.getUserById(currentUserId));
        listView.setAdapter(studentAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), BookLeassonActivity.class);
                TutorsStudent selectedStudent = studentAdapter.getStudent(position);
                intent.putExtra(TutorsStudent.class.getSimpleName(),  selectedStudent.studentId);
                startActivity(intent);
            }
        });
    }
}