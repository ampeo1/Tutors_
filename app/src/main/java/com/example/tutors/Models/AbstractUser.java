package com.example.tutors.Models;

import java.util.UUID;

public abstract class AbstractUser {

    public String id;

    public String username;

    public String mail;

    public UserRole userRole;

    private SubscriptionType subscriptionType;

    public String imagePath;

    public AbstractUser() {
    }

    public AbstractUser(String id, String username, String mail, UserRole userRole, SubscriptionType subscriptionType) {
        this.id = id;
        this.username = username;
        this.mail = mail;
        this.userRole = userRole;
        this.subscriptionType = subscriptionType;
        this.imagePath = "src/main/res/drawable/anonim.png";
    }

    public String getId(){
        return id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getMail(){
        return this.mail;
    }

    public String getImagePath() { return this.imagePath; }

    public SubscriptionType getSubscriptionType() { return this.subscriptionType; }
}
