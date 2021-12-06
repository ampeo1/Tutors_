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
import com.example.tutors.Models.Student;
import com.example.tutors.Models.Tutor;
import com.example.tutors.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private AbstractUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*        String currentUserId = FirebaseHelper.getIdCurrentUser();
        FirebaseHelper.getUserById(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshots: snapshot.getChildren()) {
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
        setupButtonWithActivity(R.id.btnToLessonsPage, TutorLessonActivity.class);
    }

    private void setupButtonWithActivity(int buttonId, Class<?> cls){
        Button btn = findViewById(buttonId);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), cls);
            startActivity(intent);
        });
    }
}