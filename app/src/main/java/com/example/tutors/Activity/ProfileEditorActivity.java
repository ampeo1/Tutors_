package com.example.tutors.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.AbstractUser;
import com.example.tutors.Models.Guest;
import com.example.tutors.Models.Student;
import com.example.tutors.Models.Tutor;
import com.example.tutors.R;
import com.example.tutors.ViewModels.ProfileEditorViewModel;
import com.example.tutors.ViewModels.SignInViewModel;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfileEditorActivity extends AppCompatActivity {
    static final int GALLERY_REQUEST = 1;
    private ProfileEditorViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);
        this.viewModel = new ViewModelProvider(this).get(ProfileEditorViewModel.class);

        String currentUserId = FirebaseHelper.getIdCurrentUser();
        FirebaseHelper.getUserById(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshots: snapshot.getChildren()) {
                    if (viewModel.getUser() == null){
                        Class<?> userClass = FirebaseHelper.getUserClass(userSnapshots);
                        viewModel.setUser((AbstractUser) userSnapshots.getValue(userClass));
                        setupActivity(viewModel.getUser());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Button btn = (Button)findViewById(R.id.saveButtonEditPage);
        btn.setOnClickListener(v -> saveUser());

        btn = (Button) findViewById(R.id.btnChangeAvatar);
        btn.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            AbstractUser user = viewModel.getUser();
            FirebaseHelper.SaveAvatar(imageReturnedIntent.getData(), user);
            setUserAvatar(imageReturnedIntent.getData().toString());
        }
    }

    private void setupStudentActivity(Student student){
        setupBaseUserActivity(student);
        hideView(R.id.etDescriptionInEditPage);
        hideView(R.id.twItemsInEditPage);
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

    private void setupActivity(AbstractUser user) {
        if (user instanceof Tutor) {
            setupTutorActivity((Tutor)user);
        }
        if (user instanceof Guest) {
            setupGuestActivity((Guest)user);
        }
        if (user instanceof Student) {
            setupStudentActivity((Student)user);
        }
    }

    private void saveUser(){
        AbstractUser user = viewModel.getUser();
        if (user instanceof Tutor) {
            setupTutorActivity((Tutor)user);
        }
        if (user instanceof Guest) {
            saveUser((Guest)user);
        }
        if (user instanceof Student) {
            setupStudentActivity((Student)user);
        }

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private void saveUser(Guest guest){
        guest.setFirstName(getTextView(R.id.etFirstNameInEditPage));
        guest.setLastName(getTextView(R.id.etLastNameInEditPage));
        guest.setPhoneNumber(getTextView(R.id.etPhoneNumberInEditPage));
        FirebaseHelper.addUser(guest);
    }

    private void setupBaseUserActivity(AbstractUser user) {
        setTextView(R.id.etFirstNameInEditPage, user.getFirstName());
        setTextView(R.id.etLastNameInEditPage, user.getLastName());
        setTextView(R.id.etPhoneNumberInEditPage, user.getPhoneNumber());
        setUserAvatar(user.getImagePath());
    }

    private void setTextView(int idEditText, String value) {
        ((TextView)findViewById(idEditText)).setText(value);
    }

    private void setUserAvatar(String path) {
        ImageView imageView = (ImageView) findViewById(R.id.iwAvatarInEditPage);
        Picasso.with(getApplicationContext())
                .load(path)
                .into(imageView);
    }

    private void hideView(int idView) {
        findViewById(idView).setVisibility(View.GONE);
    }

    private String getTextView(int idTextView) {
        return ((TextView)findViewById(idTextView)).getText().toString();
    }
}