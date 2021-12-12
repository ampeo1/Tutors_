package com.example.tutors.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.tutors.Helpers.FirebaseHelper;
import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.Models.Student;
import com.example.tutors.Models.Tutor;
import com.example.tutors.Models.TutorsStudent;
import com.example.tutors.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<TutorsStudent> students;

    public StudentAdapter(Context context, String currentUserId) {
        ctx = context;
        this.students = new ArrayList<>();
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        FirebaseHelper.getUserById(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot studentSnapshots: snapshot.getChildren()) {
                    Tutor tutor = studentSnapshots.getValue(Tutor.class);
                    students = tutor.students;
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
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public TutorsStudent getStudent(int position) { return students.get(position); }

    public void add(TutorsStudent student)
    {
        students.add(student);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.student_item, parent, false);
        }

        TutorsStudent student = getStudent(position);
        if (student.status)
        {
            TextView fName = view.findViewById(R.id.itemStudentFirstName);
            TextView lName = view.findViewById(R.id.itemStudentLastName);
            ImageView avatar = view.findViewById(R.id.itemStudentAvatar);
            FirebaseHelper.getUserById(student.studentId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot studentSnapshots: snapshot.getChildren()) {
                        Student student1 = studentSnapshots.getValue(Student.class);
                        fName.setText(student1.firstName);
                        lName.setText(student1.lastName);
                        if(student1.getImagePath() == null){
                            avatar.setImageResource(R.drawable.anonim);
                        }
                        else {
                            Picasso.with(ctx)
                                    .load(student1.getImagePath())
                                    .into(avatar);
                        }
                    }

                    notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getStringItems(String[] items_array_string,
                                  List<ItemsTypes> itemsTypes)
    {
        List<String> resArray = new ArrayList<>();
        for(ItemsTypes item: itemsTypes)
        {
            resArray.add(items_array_string[item.ordinal()]);
        }

        return String.join(", ", resArray);
    }
}
