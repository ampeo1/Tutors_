package com.example.tutors.ViewModels;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SignInViewModel extends AndroidViewModel {
    private static final String TAG = "Google Activity";
    private final ArrayList<AuthUI.IdpConfig> providers;

    public SignInViewModel(@NonNull Application application) {
        super(application);

        this.providers = new ArrayList<>(Collections.singletonList(new AuthUI.IdpConfig.GoogleBuilder().build()));
    }

    public ArrayList<AuthUI.IdpConfig> getProviders() {
        return providers;
    }

    public void setCurrentUser(Intent data){
        String userId = this.getUserId(data);

    }

    private String getUserId(Intent data){
        String id = null;
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            id = task.getResult(ApiException.class).getId();
            Log.d(TAG, id);
        }
        catch(ApiException e){
            Log.w(TAG, "Sign in failed", e);
        }

        return id;
    }
}
