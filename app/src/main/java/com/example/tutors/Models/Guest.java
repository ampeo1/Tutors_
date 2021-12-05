package com.example.tutors.Models;

import com.google.firebase.auth.FirebaseUser;

public class Guest extends AbstractUser{
    public Guest(){
        super();
    }

    public Guest(String id, String firstname, String lastname, String phoneNumber) {
        super(id, firstname, lastname, UserRole.GUEST, SubscriptionType.BASE, phoneNumber);
    }

    public Guest(FirebaseUser user) {
        super(user.getUid(), user.getDisplayName(), user.getEmail(), UserRole.GUEST, SubscriptionType.BASE, user.getPhoneNumber());
    }
}
