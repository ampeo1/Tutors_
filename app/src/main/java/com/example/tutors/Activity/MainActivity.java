package com.example.tutors.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tutors.Activity.SearchActivity;
import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.AbstractUser;
import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.Models.Lesson;
import com.example.tutors.Models.LessonStatus;
import com.example.tutors.Models.Student;
import com.example.tutors.Models.Tutor;
import com.example.tutors.Models.TutorsStudent;
import com.example.tutors.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private AbstractUser currentUser;
    private Intent intentToLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String currentUserId = FirebaseHelper.getIdCurrentUser();
        /*FirebaseHelper.getUserById(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshots: snapshot.getChildren()) {
                    Tutor currentUser2 = (Tutor) userSnapshots.getValue(Tutor.class);
                    TutorsStudent tutorsStudent = new TutorsStudent("BCPp7KtKl1hKnLAYzpCdkJXCpCC3", true);
                    currentUser2.students = new ArrayList<>();
                    currentUser2.students.add(tutorsStudent);
                    FirebaseHelper.addUser(currentUser2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                currentUser = null;
            }

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshots: snapshot.getChildren()) {
                    Class<?> a = FirebaseHelper.getUserClass(userSnapshots);
                    if (a.toString().equals(Student.class.toString()))
                    {
                        currentUser = userSnapshots.getValue(Student.class);
                        intentToLesson = new Intent(getApplicationContext(), StudentLessonActivity.class);
                    }
                    else if (a.toString().equals(Tutor.class.toString()))
                    {
                        currentUser = userSnapshots.getValue(Tutor.class);
                        intentToLesson = new Intent(getApplicationContext(), TutorLessonActivity.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                currentUser = null;
            }
        });*/
        setupButtons();
    }

    private void setupButtons(){
        setupButtonWithActivity(R.id.btnSearch, SearchActivity.class);
        setupButtonWithActivity(R.id.btnEditProfile, ProfileEditorActivity.class);
        Button btn = findViewById(R.id.btnToLessonsPage);
        btn.setOnClickListener(v -> {
            startActivity(intentToLesson);
        });

        btn = findViewById(R.id.exitAccount);
        btn.setOnClickListener(v -> {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();

            GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            FirebaseAuth.getInstance().signOut();

            mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Intent intent = new Intent(getApplicationContext(), GoogleSignInActivity.class);
                    startActivity(intent);
                }
            });
        });
    }

    private void setupButtonWithActivity(int buttonId, Class<?> cls){
        Button btn = findViewById(buttonId);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), cls);
            startActivity(intent);
        });
    }
}