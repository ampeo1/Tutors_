package com.example.tutors.ViewModels;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.AndroidViewModel;

import com.example.tutors.Activity.MainActivity;
import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.Guest;
import com.example.tutors.Models.Student;
import com.example.tutors.Models.Tutor;
import com.example.tutors.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

public class SignInViewModel extends AndroidViewModel {
    private final ArrayList<AuthUI.IdpConfig> providers;

    public SignInViewModel(@NonNull Application application) {
        super(application);

        this.providers = new ArrayList<>(Collections.singletonList(new AuthUI.IdpConfig.GoogleBuilder().build()));
    }

    public ArrayList<AuthUI.IdpConfig> getProviders() {
        return providers;
    }

    public void setCurrentUser(FirebaseUser user, Context context){
        if (user != null && !isRegisteredUser(Objects.requireNonNull(user.getMetadata()))){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            String[] items = context.getResources().getStringArray(R.array.typesUsers);
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (items[which].equals("Студент")){
                        FirebaseHelper.addUser(new Student(user));
                    }

                    if (items[which].equals("Репетитор")){
                        FirebaseHelper.addUser(new Tutor(user));
                    }

                    context.startActivity(new Intent(context, MainActivity.class));
                }
            });

            builder.setCancelable(false);
            builder.create().show();
        }
        else {
            context.startActivity(new Intent(context, MainActivity.class));
        }
    }

    public boolean isRegisteredUser(FirebaseUserMetadata metadata){
        int inaccuracy = 2;
        return Math.abs(metadata.getCreationTimestamp() - metadata.getLastSignInTimestamp()) > inaccuracy;
    }
}
