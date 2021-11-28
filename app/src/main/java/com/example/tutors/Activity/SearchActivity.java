package com.example.tutors.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.tutors.Adapters.SearchTutorsAdapter;
import com.example.tutors.Helpers.Database;
import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.Models.Tutor;
import com.example.tutors.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListView listView = findViewById(R.id.listViewTutors);
        SearchTutorsAdapter searchTutorsAdapter = new SearchTutorsAdapter(this, Database.getTutors());

        listView.setAdapter(searchTutorsAdapter);
    }
}