package com.example.tutors.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.AbstractUser;
import com.example.tutors.Models.Guest;
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

        String currentUserId = FirebaseHelper.getIdCurrentUser();
        FirebaseHelper.getUserById(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshots: snapshot.getChildren()) {
                    Class<?> userClass = FirebaseHelper.getUserClass(userSnapshots);
                    setupActivity(userSnapshots.getValue(userClass));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupGuestActivity(Guest guest){
        setupBaseUserActivity(guest);
        hideView(R.id.etDescriptionInEditPage);
        hideView(R.id.twItemsInEditPage);
        hideView(R.id.twRatingInEditPage);
    }

    private void setupTutorActivity(Tutor tutor){
        setupBaseUserActivity(tutor);
    }

    private void setupActivity(Object user) {
        if (user instanceof Tutor) {
            setupTutorActivity((Tutor)user);
        }
        if (user instanceof Guest) {
            setupGuestActivity((Guest)user);
        }

    }

    private void setupBaseUserActivity(AbstractUser user) {
        setTextView(R.id.etFirstNameInEditPage, user.getFirstName());
        setTextView(R.id.etLastNameInEditPage, user.getLastName());
        setTextView(R.id.etPhoneNumberInEditPage, user.getPhoneNumber());
    }

    private void setTextView(int idEditText, String value) {
        ((TextView)findViewById(idEditText)).setText(value);
    }

    private void hideView(int idView) {
        findViewById(idView).setVisibility(View.GONE);
    }
}