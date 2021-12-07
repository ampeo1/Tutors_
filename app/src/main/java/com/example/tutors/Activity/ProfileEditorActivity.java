package com.example.tutors.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.AbstractUser;
import com.example.tutors.Models.Guest;
import com.example.tutors.Models.ItemsTypes;
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

import java.util.ArrayList;

public class ProfileEditorActivity extends AppCompatActivity {
    static final int GALLERY_REQUEST = 1;
    private Spinner spinner;
    private ProfileEditorViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);
        this.viewModel = new ViewModelProvider(this).get(ProfileEditorViewModel.class);
        this.spinner = findViewById(R.id.typeUserSpinner);

        String currentUserId = FirebaseHelper.getIdCurrentUser();
        FirebaseHelper.getUserById(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshots: snapshot.getChildren()) {
                    if (viewModel.getUser() == null){
                        Class<?> userClass = FirebaseHelper.getUserClass(userSnapshots);
                        viewModel.setUser((AbstractUser) userSnapshots.getValue(userClass));
                        int itemSelected = setupActivity(viewModel.getUser());
                        setupSpinner(itemSelected);
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
            FirebaseHelper.SaveAvatar(imageReturnedIntent.getData(), viewModel.getUser(), getApplicationContext());
            setUserAvatar(imageReturnedIntent.getData().toString());
        }
    }

    private void setupStudentActivity(Student student){
        setupBaseUserActivity(student);
        hideView(R.id.etDescriptionInEditPage);
    }

    private void setupGuestActivity(Guest guest){
        setupBaseUserActivity(guest);
        hideView(R.id.etDescriptionInEditPage);
    }

    private void setupTutorActivity(Tutor tutor){
        setupBaseUserActivity(tutor);
        findViewById(R.id.etDescriptionInEditPage).setVisibility(View.VISIBLE);
    }

    private int setupActivity(AbstractUser user) {
        if (user instanceof Tutor) {
            setupTutorActivity((Tutor)user);
            return 0;
        }
        if (user instanceof Guest) {
            setupGuestActivity((Guest)user);
            return 2;
        }
        if (user instanceof Student) {
            setupStudentActivity((Student)user);
            return 1;
        }

        return 2;
    }

    private void saveUser(){
        AbstractUser user = viewModel.getUser();
        if (user instanceof Tutor) {
            saveTutor((Tutor)user);
        }
        if (user instanceof Student) {
            saveUser((Student)user);
        }
        if (user instanceof Guest) {
            saveUser((Guest)user);
        }

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private void saveTutor(Tutor user){
        user.setDescription(getTextView(R.id.etDescriptionInEditPage));
        saveUser(user);
    }

    private void saveUser(AbstractUser user){
        user.setFirstName(getTextView(R.id.etFirstNameInEditPage));
        user.setLastName(getTextView(R.id.etLastNameInEditPage));
        user.setPhoneNumber(getTextView(R.id.etPhoneNumberInEditPage));
        FirebaseHelper.addUser(user);
    }

    private void setupBaseUserActivity(AbstractUser user) {
        setTextView(R.id.etFirstNameInEditPage, user.getFirstName());
        setTextView(R.id.etLastNameInEditPage, user.getLastName());
        setTextView(R.id.etPhoneNumberInEditPage, user.getPhoneNumber());
        setUserAvatar(user.getImagePath());
    }

    private void setupSpinner(int itemSelected){
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.typesUsers, android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.typeUserSpinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(itemSelected);
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AbstractUser oldUser = viewModel.getUser();
                if (position == 0) {
                    viewModel.setUser(new Tutor(oldUser.getId(), oldUser.getFirstName(), oldUser.getLastName(), new ArrayList<ItemsTypes>(), oldUser.getPhoneNumber(), "", oldUser.getMail(), oldUser.getImagePath()));
                }
                if (position == 1){
                    viewModel.setUser(new Student(oldUser.getId(), oldUser.getFirstName(), oldUser.getLastName(), oldUser.getPhoneNumber(), oldUser.getMail(), oldUser.getImagePath()));
                }
                if (position == 2){
                    viewModel.setUser(new Guest(oldUser.getId(), oldUser.getFirstName(), oldUser.getLastName(), oldUser.getPhoneNumber(), oldUser.getMail(), oldUser.getImagePath()));
                }

                setupActivity(viewModel.getUser());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        findViewById(idView).setVisibility(View.INVISIBLE);
    }

    private String getTextView(int idTextView) {
        return ((TextView)findViewById(idTextView)).getText().toString();
    }
}