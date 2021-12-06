package com.example.tutors.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.Models.Lesson;
import com.example.tutors.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TutorLessonAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Lesson> lessons;

    public TutorLessonAdapter(Context context, Query lessonsRef) {
        ctx = context;
        this.lessons = new ArrayList<>();
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        lessonsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot lessonSnapshots: snapshot.getChildren()) {
                    lessons.add(lessonSnapshots.getValue(Lesson.class));
                }

                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getCount() {
        return lessons.size();
    }

    @Override
    public Object getItem(int position) {
        return (Lesson) lessons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Lesson getLesson(int position)
    {
        return lessons.get(position);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getStringItem(String[] items_array_string,
                                 ItemsTypes itemType)
    {
        return items_array_string[itemType.ordinal()];
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.tutor_lesson_item, parent, false);
        }

        Lesson lesson = getLesson(position);
        ((TextView) view.findViewById(R.id.twTutorLessonTopic)).setText(lesson.topic);
        if (lesson.mark != -1)
        {
            ((EditText) view.findViewById(R.id.etTutorLessonMark)).setText(Integer.toString(lesson.mark));
        }
        else
        {
            ((EditText) view.findViewById(R.id.etTutorLessonMark)).setText("");
        }
        String[] items_array_string = view.getResources().getStringArray(R.array.items_array_rus);
        ItemsTypes itemType = lesson.type;
        ((TextView) view.findViewById(R.id.twTutorLessonItem)).setText(getStringItem(items_array_string, itemType));
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        ((TextView) view.findViewById(R.id.twTutorLessonDate)).setText(dateFormat.format(lesson.dateEvent));

        Spinner filterSpinner = view.findViewById(R.id.spinnerLessonStatus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.lesson_status_array_rus, android.R.layout.simple_spinner_item);
        filterSpinner.setAdapter(adapter);
        return view;
    }
}
