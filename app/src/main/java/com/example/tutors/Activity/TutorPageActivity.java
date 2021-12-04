package com.example.tutors.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutors.Models.Tutor;
import com.example.tutors.R;

import java.net.URI;

public class TutorPageActivity extends AppCompatActivity {

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

        Tutor tutor;
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            tutor = (Tutor) arguments.getSerializable(Tutor.class.getSimpleName());

            firstName.setText(tutor.getFirstName());
            lastName.setText(tutor.getLastName());
            phoneNumber.setText(tutor.getPhoneNumber());
            rating.setText(tutor.getStringRating());
            items.setText(tutor.getStringItems());
            description.setText(tutor.getDescription());
            // avatar.setImageURI(URI.parse("file://mnt/sdcard/cat.jpg"));
        }
    }
}