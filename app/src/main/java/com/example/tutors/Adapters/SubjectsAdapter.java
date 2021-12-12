package com.example.tutors.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectsAdapter extends BaseAdapter {
    private List<ItemsTypes> items;
    private LayoutInflater inflater;

    public SubjectsAdapter(Context context, List<ItemsTypes> items) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getSubject().hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.subject_item, parent, false);
        TextView textView = view.findViewById(R.id.subjectName);
        textView.setText(items.get(position).getSubject());

        ImageButton btn = (ImageButton) view.findViewById(R.id.deleteSubjectFromList);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}