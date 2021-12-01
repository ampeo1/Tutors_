package com.example.tutors.Helpers;

import androidx.annotation.NonNull;

import com.example.tutors.Models.Tutor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseHelper {
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static final DatabaseReference tutorsRef = database.getReference("users/tutors");

    public static void addUser(Tutor tutor) {
        tutorsRef.child(tutor.getId()).setValue(tutor);
    }

    public static DatabaseReference getTutorsRef() {
        return tutorsRef;
    }
}
