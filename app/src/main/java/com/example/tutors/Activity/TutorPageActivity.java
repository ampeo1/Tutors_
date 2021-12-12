package com.example.tutors.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.Models.Student;
import com.example.tutors.Models.Tutor;
import com.example.tutors.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TutorPageActivity extends AppCompatActivity {
    private Tutor tutor;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_page);

        TextView firstName = findViewById(R.id.twFirstNameInTutorPage);
        TextView lastName = findViewById(R.id.twLastNameInTutorPage);
        TextView phoneNumber = findViewById(R.id.twPhoneNumberInTutorPage);
        TextView rating = findViewById(R.id.twRatingInTutorPage);
        TextView items = findViewById(R.id.twItemsInTutorPage);
        TextView description = findViewById(R.id.twDescriptionInTutorPage);
        ImageView avatar = findViewById(R.id.iwAvatarInTutorPage);
        avatar.setImageResource(R.drawable.anonim);

        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            tutor = (Tutor) arguments.getSerializable(Tutor.class.getSimpleName());

            firstName.setText(tutor.getFirstName());
            lastName.setText(tutor.getLastName());
            phoneNumber.setText(tutor.getPhoneNumber());
            rating.setText(tutor.getStringRating());
            items.setText(getStringItems(tutor));
            description.setText(tutor.getDescription());
            if (tutor.getImagePath() != null){
                Picasso.with(getApplicationContext())
                        .load(tutor.getImagePath())
                        .into(avatar);
            }
        }

        Button btn = findViewById(R.id.btnBookTutor);
        // todo invite
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getStringItems(Tutor tutor)
    {
        String[] items_array_string = getResources().getStringArray(R.array.items_array_rus);
        List<ItemsTypes> itemsTypes = tutor.getItems();

        List<String> resArray = new ArrayList<>();
        for(ItemsTypes item: itemsTypes)
        {
            resArray.add(items_array_string[item.ordinal()]);
        }

        return String.join(", ", resArray);
    }
}