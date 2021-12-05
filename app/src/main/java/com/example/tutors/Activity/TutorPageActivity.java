package com.example.tutors.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.Models.Tutor;
import com.example.tutors.R;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class TutorPageActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_page);

        TextView firstName = findViewById(R.id.twFirstNameInTutorPage);
        TextView lastName = findViewById(R.id.twLastNameInTutorPage);
        TextView phoneNumber = findViewById(R.id.twPhoneNumberInTutorPage);
        TextView rating = findViewById(R.id.twRatingInTutorPage);
        TextView items = findViewById(R.id.twItemsInTutorPage);
        TextView description = findViewById(R.id.twDescriptionInTutorPage);
        ImageView avatar = findViewById(R.id.iwAvatarInTutorPage);

        Tutor tutor;
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            tutor = (Tutor) arguments.getSerializable(Tutor.class.getSimpleName());

            firstName.setText(tutor.getFirstName());
            lastName.setText(tutor.getLastName());
            phoneNumber.setText(tutor.getPhoneNumber());
            rating.setText(tutor.getStringRating());
            items.setText(getStringItems(tutor));
            description.setText(tutor.getDescription());
            // avatar.setImageURI(URI.parse("file://mnt/sdcard/cat.jpg"));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getStringItems(Tutor tutor)
    {
        String[] items_array_string = getResources().getStringArray(R.array.items_array_rus);
        List<ItemsTypes> itemsTypes = tutor.getItems();

        List<String> resArray = new ArrayList<>();
        for(ItemsTypes item: itemsTypes)
        {
            resArray.add(items_array_string[item.ordinal()]);
        }

        return String.join(", ", resArray);
    }
}