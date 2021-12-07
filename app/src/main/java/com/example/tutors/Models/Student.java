package com.example.tutors.Models;

import com.google.firebase.auth.FirebaseUser;

public class Student extends AbstractUser{
    public Student(){
        super();
    }

    public Student(String id, String firstname, String lastname, String phoneNumber, String mail) {
        super(id, firstname, lastname, phoneNumber, mail);
    }

    public Student(FirebaseUser user) {
        super(user);
    }
}
