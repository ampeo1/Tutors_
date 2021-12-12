package com.example.tutors.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
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
import java.util.Arrays;

public class ProfileEditorActivity extends AppCompatActivity {
    static final int GALLERY_REQUEST = 1;
    private Spinner spinner;
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
                    }

                    setupActivity(viewModel.getUser());
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

    private void setupUserActivity(AbstractUser student){
        setupBaseUserActivity(student);
        hideView(R.id.addSubject);
        hideView(R.id.etDescriptionInEditPage);
        hideView(R.id.subjectsListView);
    }

    private void setupTutorActivity(Tutor tutor){
        setupBaseUserActivity(tutor);

        ListView listView = (ListView) findViewById(R.id.subjectsListView);
        listView.setAdapter(viewModel.createAdapter(this));

        Button btn = (Button) findViewById(R.id.addSubject);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(v.getContext());
                String[] items = getResources().getStringArray(R.array.items_array_rus);
                dialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (ItemsTypes item: ItemsTypes.values()){
                            if (item.getSubject().equals(items[which])){
                                viewModel.addSubject(item);
                                listView.deferNotifyDataSetChanged();
                            }
                        }
                    }
                });
                dialogBuilder.setTitle("Выбор предмета");
                dialogBuilder.create().show();
            }
        });
    }

    private void setupActivity(AbstractUser user) {
        if (user instanceof Tutor) {
            setupTutorActivity((Tutor)user);
        }
        else {
            setupUserActivity(user);
        }
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