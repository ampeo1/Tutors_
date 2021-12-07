package com.example.tutors.Helpers;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.tutors.Models.AbstractUser;
import com.example.tutors.Models.Lesson;
import com.example.tutors.Models.Tutor;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class FirebaseHelper {
    private static final String EXCEPTION_TAG = "FirebaseHelperException";
    private static final String TYPE_PROPERTY_NAME = "type";
    private static final String LESSON_STUDENT_ID_PROPERTY = "studentId";
    private static final String LESSON_TUTOR_ID_PROPERTY = "tutorId";
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static final DatabaseReference usersRef = database.getReference("users");
    private static final DatabaseReference lessonsRef = database.getReference("lessons");

    private static final Query tutorsQuery = usersRef.orderByChild(TYPE_PROPERTY_NAME).equalTo(Tutor.class.getName());

    public static <T extends  AbstractUser> void addUser(T user) {
        HashMap<String, Object> map = new HashMap(){{
                put(TYPE_PROPERTY_NAME, user.getClass().getName());
            }};
        usersRef.child(user.getId()).setValue(user);
        usersRef.child(user.getId()).updateChildren(map);
    }

    public static Query getTutors() {
        return tutorsQuery;
    }

    public static Query getUserById(String userId) {
        return usersRef.orderByKey().equalTo(userId);
    }

    public static void addLesson(Lesson lesson) {
        if (lesson.getId() == null){
            lesson.setId(UUID.randomUUID().toString());
        }

        lessonsRef.child(lesson.getId()).setValue(lesson);
    }

    public static Query getLessonByStudentId(String studentId) {
        return lessonsRef.orderByChild(LESSON_STUDENT_ID_PROPERTY).equalTo(studentId);
    }

    public static Query getLessonByTutorId(String tutorId) {
        return lessonsRef.orderByChild(LESSON_TUTOR_ID_PROPERTY).equalTo(tutorId);
    }

    public static String getIdCurrentUser(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public static Class<?> getUserClass(DataSnapshot snapshot){
        Class<?> userClass = Object.class;
        for (DataSnapshot property: snapshot.getChildren()) {
            if(Objects.equals(property.getKey(), TYPE_PROPERTY_NAME)) {
                try {
                    userClass = Class.forName(Objects.requireNonNull(property.getValue(String.class)));
                } catch (ClassNotFoundException e) {
                    Log.e(EXCEPTION_TAG, e.getMessage());
                }
            }
        }

        return userClass;
    }

    public static void SaveAvatar(Uri uri, AbstractUser user){
        if (uri == null){
            return;
        }
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        final StorageReference riversRef = storageRef.child("avatars/" + uri.getLastPathSegment());
        UploadTask uploadTask = riversRef.putFile(uri);

        uploadTask.continueWithTask(task -> {
            if(!task.isSuccessful()){
                throw task.getException();
            }

            return riversRef.getDownloadUrl();
        }).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                final Uri downloadUri = task.getResult();
                user.setImagePath(downloadUri.toString());
            }
        });
    }
}
