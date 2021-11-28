package com.example.tutors.Models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Tutor extends AbstractUser{

    public List<ItemsTypes> items;

    public Tutor(){
        super();

    }

    public Tutor(String firstName, String lastName, List<ItemsTypes> items)
    {
        super(firstName, lastName, UserRole.TUTOR, SubscriptionType.BASE);

        this.items = items;
    }

    public String itemsToString()
    {
        StringBuilder sb = new StringBuilder();
        for (ItemsTypes item: this.items) {
            sb.append(item.toString());
            sb.append("; ");
        }

        return sb.toString();
    }

}
