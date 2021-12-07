package com.example.tutors.Models;

import com.google.firebase.auth.FirebaseUser;

public class Student extends AbstractUser{
    public Student(){
        super();
    }

    public Student(String id, String firstname, String lastname, String phoneNumber) {
        super(id, firstname, lastname, UserRole.GUEST, SubscriptionType.BASE, phoneNumber);
    }

    public Student(FirebaseUser user) {
        super(user.getUid(), user.getDisplayName(), user.getEmail(), UserRole.GUEST, SubscriptionType.BASE, user.getPhoneNumber());
    }
}
