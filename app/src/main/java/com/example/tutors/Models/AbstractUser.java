package com.example.tutors.Models;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractUser implements Serializable {
    public String id;
    public String firstName;
    public String lastName;
    public String imagePath;
    public String phoneNumber;
    public String mail;

    public AbstractUser() {
    }

    public AbstractUser(String id, String firstName, String lastName, String phoneNumber, String mail, String imagePath) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imagePath = imagePath;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }

    public AbstractUser(FirebaseUser user){
        this.id = user.getUid();
        setFirstAndLastName(Objects.requireNonNull(user.getDisplayName()));
        this.imagePath = user.getPhotoUrl().toString();
        this.phoneNumber = user.getPhoneNumber();
        this.mail = user.getEmail();
    }

    public void setFirstName(String firstName) {
        if (checkString(firstName)) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (checkString(lastName)) {
            this.lastName = lastName;
        }
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (checkString(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        }
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        if (checkString(mail)) {
            this.mail = mail;
        }
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

    public String getImagePath() {
        return imagePath;
    }

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

    private boolean checkString(String value) {
        return value != null && !value.equals("");
    }
}
