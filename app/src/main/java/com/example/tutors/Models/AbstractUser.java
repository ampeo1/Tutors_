package com.example.tutors.Models;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class AbstractUser implements Serializable {
    public String id;
    public String firstName;
    public String lastName;
    public String imagePath;
    public String phoneNumber;
    public String mail;

    public AbstractUser() {
    }

    public AbstractUser(String id, String firstName, String lastName, String phoneNumber, String mail) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imagePath = "src/main/res/drawable/anonim.png";
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }

    public AbstractUser(FirebaseUser user){
        this.id = user.getUid();
        setFirstAndLastName(Objects.requireNonNull(user.getDisplayName()));
        this.imagePath = "src/main/res/drawable/anonim.png";
        this.phoneNumber = user.getPhoneNumber();
        this.mail = user.getEmail();
    }

    public String getId(){
        return id;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getImagePath() { return this.imagePath; }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    private void setFirstAndLastName(String displayName){
        String[] names = displayName.split(" ");
        switch (names.length){
            case 1:
                this.firstName = names[0];
                break;
            case 2:
                this.firstName = names[0];
                this.lastName = names[1];
                break;
        }
    }
}
