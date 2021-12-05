package com.example.tutors.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.Tutor;
import com.example.tutors.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

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

        String currentUserId = FirebaseHelper.getIdCurrentUser();
        if (currentUserId != null)
        {
            FirebaseHelper.getUserById(currentUserId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot tutorSnapshots: snapshot.getChildren()) {
                        Tutor currentTutor = tutorSnapshots.getValue(Tutor.class);
                        firstName.setText(currentTutor.firstName);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}