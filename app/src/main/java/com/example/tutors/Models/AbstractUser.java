package com.example.tutors.Models;

import java.io.Serializable;
import java.util.UUID;

public abstract class AbstractUser implements Serializable {

    public final String id;

    public String firstName;

    public String lastName;

    public UserRole userRole;

    private SubscriptionType subscriptionType;

    public String imagePath;

    public String phoneNumber;

    public AbstractUser() {
        this.id = UUID.randomUUID().toString();
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public AbstractUser(String firstName, String lastName, UserRole userRole, SubscriptionType subscriptionType, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.subscriptionType = subscriptionType;
        this.imagePath = "src/main/res/drawable/anonim.png";
        this.phoneNumber = phoneNumber;
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
}
