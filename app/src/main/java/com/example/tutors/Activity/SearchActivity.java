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
import com.example.tutors.Helpers.Database;
import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.Models.Tutor;
import com.example.tutors.R;

import java.util.ArrayList;
import java.util.List;

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
        SearchTutorsAdapter searchTutorsAdapter = new SearchTutorsAdapter(this, Database.getTutors());

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