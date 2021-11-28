package com.example.tutors.Helpers;

import androidx.annotation.NonNull;

import com.example.tutors.Models.Tutor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Database {
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static final DatabaseReference tutorsRef = database.getReference("users/tutors");

    public static void addUser(Tutor tutor) {
        tutorsRef.child(tutor.getId()).setValue(tutor);
    }

    public static ArrayList<Tutor> getTutors() {
        ArrayList<Tutor> tutors = new ArrayList<>();
        tutorsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot tutorShanpshot: snapshot.getChildren()) {
                    Tutor tutor = tutorShanpshot.getValue(Tutor.class);
                    tutors.add(tutor);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return tutors;
    }
}
