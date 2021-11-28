package com.example.tutors.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.tutors.Adapters.SearchTutorsAdapter;
import com.example.tutors.Models.AbstractUser;
import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.Models.Tutor;
import com.example.tutors.R;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private Spinner filterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        filterSpinner = findViewById(R.id.filterItemsSpinner);
        ItemsTypes[] allItems = ItemsTypes.class.getEnumConstants();
        String[] allItemsString = new String[allItems.length];
        for (int i = 0; i < allItems.length; i++)
        {
            allItemsString[i] = allItems[i].toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, allItemsString);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);

        ListView listView = findViewById(R.id.listViewTutors);

        ItemsTypes[] itemsTypes1 = new ItemsTypes[] {ItemsTypes.MATHS};
        ItemsTypes[] itemsTypes2 = new ItemsTypes[] {ItemsTypes.PHYSICS};
        ItemsTypes[] itemsTypes3 = new ItemsTypes[] {ItemsTypes.MATHS, ItemsTypes.PHYSICS};
        ArrayList<Tutor> tutors = new ArrayList<Tutor>();
        tutors.add(new Tutor("Nikita", "Sidarenka", itemsTypes1));
        tutors.add(new Tutor("Nikita", "Sidarenka", itemsTypes1));
        tutors.add(new Tutor("Nikita", "Sidarenka", itemsTypes2));
        tutors.add(new Tutor("Nikita", "Sidarenka", itemsTypes2));
        tutors.add(new Tutor("Nikita", "Sidarenka", itemsTypes3));
        tutors.add(new Tutor("Nikita", "Sidarenka", itemsTypes3));
        // используем адаптер данных


        SearchTutorsAdapter searchTutorsAdapter = new SearchTutorsAdapter(this, tutors);
        listView.setAdapter(searchTutorsAdapter);
        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                searchTutorsAdapter.getFilter().filter(filterSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

    }
}