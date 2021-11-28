package com.example.tutors.Models;

import java.util.UUID;

public abstract class AbstractUser {

    private String id;

    private String firstName;

    private String lastName;

    private UserRole userRole;

    private SubscriptionType subscriptionType;

    private String imagePath;

    public AbstractUser(String firstName, String lastName, UserRole userRole, SubscriptionType subscriptionType) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
        this.subscriptionType = subscriptionType;
        this.imagePath = "src/main/res/drawable/anonim.png";
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getImagePath() { return this.imagePath; }

    public SubscriptionType getSubscriptionType() { return this.subscriptionType; }
}
