package com.example.tutors.Models;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class AbstractUser implements Serializable {

    public String id;

    public String firstName;

    public String lastName;

    public UserRole userRole;

    private SubscriptionType subscriptionType;

    public String imagePath;

    public String phoneNumber;

    public AbstractUser() {
    }

    public AbstractUser(String id, String firstName, String lastName, UserRole userRole, SubscriptionType subscriptionType, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.subscriptionType = subscriptionType;
        this.imagePath = "src/main/res/drawable/anonim.png";
        this.phoneNumber = phoneNumber;
    }

    public AbstractUser(FirebaseUser user, UserRole userRole, SubscriptionType subscriptionType){
        this.id = user.getUid();
        String test = user.getEmail();
        setFirstAndLastName(user.getDisplayName());
        this.userRole = userRole;
        this.subscriptionType = subscriptionType;
        this.imagePath = "src/main/res/drawable/anonim.png";
        this.phoneNumber = user.getPhoneNumber();
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

    public SubscriptionType getSubscriptionType() { return this.subscriptionType; }

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
