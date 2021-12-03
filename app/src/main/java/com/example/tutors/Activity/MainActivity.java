package com.example.tutors.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tutors.Activity.SearchActivity;
import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.Models.Tutor;
import com.example.tutors.R;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseHelper.addUser(new Tutor("Aleh", "Yahaudzik", Arrays.asList(ItemsTypes.MATHS)));
        setContentView(R.layout.activity_main);
        setupButtons();
    }

    private void setupButtons(){
        setupButtonWithActivity(R.id.btnSearch, SearchActivity.class);
        setupButtonWithActivity(R.id.btnEditProfile, ProfileEditorActivity.class);
    }

    private void setupButtonWithActivity(int buttonId, Class<?> cls){
        Button btn = findViewById(buttonId);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), cls);
            startActivity(intent);
        });
    }
}