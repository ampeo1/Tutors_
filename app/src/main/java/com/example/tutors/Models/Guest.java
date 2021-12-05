package com.example.tutors.Models;

import com.google.firebase.auth.FirebaseUser;

public class Guest extends AbstractUser{
    public Guest(){
        super();
    }

    public Guest(String id, String username, String mail) {
        super(id, username, mail, UserRole.GUEST, SubscriptionType.BASE);
    }

    public Guest(FirebaseUser user) {
        super(user.getUid(), user.getDisplayName(), user.getEmail(), UserRole.GUEST, SubscriptionType.BASE);
    }
}
