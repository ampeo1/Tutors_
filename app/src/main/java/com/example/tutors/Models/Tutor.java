package com.example.tutors.Models;

import com.example.tutors.R;
import com.firebase.ui.auth.data.model.PhoneNumber;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.List;

@IgnoreExtraProperties
public class Tutor extends AbstractUser {

    public List<ItemsTypes> items;

    public Tutor(){
        super();

    }

    public String description;

    public float rating;

    public Tutor(String id, String firstName, String lastName, List<ItemsTypes> items, String phoneNumber, String description, String mail, String imagePath)
    {
        super(id, firstName, lastName, phoneNumber, mail, imagePath);

        this.description = description;
        this.items = items;
        this.rating = 0.0f;
    }

    public List<ItemsTypes> getItems()
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

    public void setDescription(String description)
    {
        if (description.length() <= 255)
        {
            this.description = description;
        }
    }
}