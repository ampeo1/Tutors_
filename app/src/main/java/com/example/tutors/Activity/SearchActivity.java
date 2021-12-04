package com.example.tutors.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.tutors.Adapters.SearchTutorsAdapter;
import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.R;

import java.util.ArrayList;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchTutorsAdapter listViewAdapter = setupListView();
        setupSpinner(listViewAdapter);
    }

    private void setupSpinner(SearchTutorsAdapter searchTutorsAdapter) {
        Spinner filterSpinner = findViewById(R.id.filterItemsSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getSubjects());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);

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

    private SearchTutorsAdapter setupListView() {
        ListView listView = findViewById(R.id.listViewTutors);
        SearchTutorsAdapter searchTutorsAdapter = new SearchTutorsAdapter(this, FirebaseHelper.getTutors());
        listView.setAdapter(searchTutorsAdapter);

        return searchTutorsAdapter;
    }

    private ArrayList<String> getSubjects() {
        ArrayList<String> subjects = new ArrayList<>();
        for(ItemsTypes item: Objects.requireNonNull(ItemsTypes.class.getEnumConstants())) {
            subjects.add(item.toString().replace('_', ' '));
        }

        return subjects;
    }
}