package com.example.tutors.Models;

public class Tutor extends AbstractUser{

    private ItemsTypes[] items;

    private String description;

    private float rating;

    public Tutor(String firstName, String lastName, ItemsTypes[] items)
    {
        super(firstName, lastName, UserRole.TUTOR, SubscriptionType.BASE);

        this.items = items;
        this.rating = 0.0f;
    }

    public ItemsTypes[] getItems()
    {
        return this.items;
    }

    public String getStringItems()
    {
        StringBuilder sb = new StringBuilder();
        for (ItemsTypes item: this.items) {
            sb.append(item.toString());
            sb.append("; ");
        }

        return sb.toString();
    }

    public void setRating(float rating)
    {
        if (rating <= 5)
        {
            this.rating = rating;
        }
    }

    public void setDescription(String description)
    {
        if (description.length() <= 255)
        {
            this.description = description;
        }
    }

    public float getRating()
    {
        return this.rating;
    }

    public String getStringRating()
    {
        return Float.toString(this.rating) + "/5.0";
    }

    public String getDescription()
    {
        return this.description;
    }
}