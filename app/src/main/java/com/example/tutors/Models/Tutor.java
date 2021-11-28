package com.example.tutors.Models;

public class Tutor extends AbstractUser{

    private ItemsTypes[] items;

    public Tutor(String firstName, String lastName, ItemsTypes[] items)
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
