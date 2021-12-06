package com.example.tutors.ViewModels;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.Guest;
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

    public void setCurrentUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null && !isRegisteredUser(Objects.requireNonNull(user.getMetadata()))){
            FirebaseHelper.addUser(new Guest(user));
        }
    }

    public boolean isRegisteredUser(FirebaseUserMetadata metadata){
        int inaccuracy = 2;
        return Math.abs(metadata.getCreationTimestamp() - metadata.getLastSignInTimestamp()) > inaccuracy;
    }
}
