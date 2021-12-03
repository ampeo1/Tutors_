package com.example.tutors.Helpers;

import com.example.tutors.Models.AbstractUser;
import com.example.tutors.Models.Tutor;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FirebaseHelper {
    private static final String TYPE_PROPERTY_NAME = "type";
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static final DatabaseReference usersRef = database.getReference("users");

    private static final Query tutorsQuery = usersRef.orderByChild(TYPE_PROPERTY_NAME).equalTo(Tutor.class.getName());

    public static <T extends  AbstractUser> void addUser(T user) {
        HashMap<String, Object> map = new HashMap(){{
                put(TYPE_PROPERTY_NAME, user.getClass().getName());
            }};
        usersRef.child(user.getId()).setValue(user);
        usersRef.child(user.getId()).updateChildren(map);
    }

    public static Query getTutors() {
        return tutorsQuery;
    }

    public static void registerUser(){
        //this.tutorsRef.
    }
}
