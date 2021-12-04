package com.example.tutors.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutors.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);

        EditText firstName = findViewById(R.id.etFirstNameInEditPage);
        EditText lastName = findViewById(R.id.etLastNameInEditPage);
        EditText phoneNumber = findViewById(R.id.etPhoneNumberInEditPage);
        TextView rating = findViewById(R.id.twRatingInEditPage);
        TextView items = findViewById(R.id.twItemsInEditPage);
        EditText description = findViewById(R.id.etDescriptionInEditPage);
        ImageView avatar = findViewById(R.id.iwAvatarInEditPage);
    }
}