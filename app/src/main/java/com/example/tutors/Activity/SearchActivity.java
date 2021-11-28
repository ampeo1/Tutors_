package com.example.tutors.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tutors.Adapters.SearchTutorsAdapter;
import com.example.tutors.Models.AbstractUser;
import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.Models.Tutor;
import com.example.tutors.R;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListView listView = findViewById(R.id.listViewTutors);

        ItemsTypes[] itemsTypes = new ItemsTypes[] {ItemsTypes.MATHS, ItemsTypes.PHYSICS};
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        tutors.add(new Tutor("Nikita", "Sidarenka", itemsTypes));
        tutors.add(new Tutor("Nikita", "Sidarenka", itemsTypes));
        tutors.add(new Tutor("Nikita", "Sidarenka", itemsTypes));
        tutors.add(new Tutor("Nikita", "Sidarenka", itemsTypes));
        tutors.add(new Tutor("Nikita", "Sidarenka", itemsTypes));
        tutors.add(new Tutor("Nikita", "Sidarenka", itemsTypes));
        tutors.add(new Tutor("Nikita", "Sidarenka", itemsTypes));
        // используем адаптер данных

        SearchTutorsAdapter searchTutorsAdapter = new SearchTutorsAdapter(this, tutors);

        listView.setAdapter(searchTutorsAdapter);
    }
}