package com.example.tutors.Models;

import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Guest extends AbstractUser{
    public Guest(){
        super();
    }

    public Guest(String id, String firstname, String lastname, String phoneNumber, String mail) {
        super(id, firstname, lastname, phoneNumber, mail);
    }

    public Guest(FirebaseUser user) {
        super(user);
    }
}
