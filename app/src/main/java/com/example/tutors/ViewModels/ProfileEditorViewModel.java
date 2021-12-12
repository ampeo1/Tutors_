package com.example.tutors.ViewModels;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import com.example.tutors.Adapters.SubjectsAdapter;
import com.example.tutors.Models.AbstractUser;
import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.Models.Tutor;

import java.util.ArrayList;

public class ProfileEditorViewModel extends AndroidViewModel {
    private SubjectsAdapter subjectsAdapter;
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


    public SubjectsAdapter createAdapter(Context context){
        this.subjectsAdapter = new SubjectsAdapter(context, ((Tutor) user).getItems());
        return subjectsAdapter;
    }

    public void addSubject(ItemsTypes item) {
        if (user instanceof Tutor){
            Tutor tutor = (Tutor) user;
            tutor.items.add(item);
            subjectsAdapter.notifyDataSetChanged();
        }
    }
}
