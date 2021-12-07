package com.example.tutors.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tutors.Models.AbstractUser;

public class ProfileEditorViewModel extends AndroidViewModel {
    private AbstractUser user;

    public ProfileEditorViewModel(@NonNull Application application) {
        super(application);
    }

    public void setUser(AbstractUser user) {
        this.user = user;
    }

    public AbstractUser getUser(){
        return user;
    }
}
