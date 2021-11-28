package com.example.tutors.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tutors.Models.Tutor;
import com.example.tutors.R;

import java.util.ArrayList;

public class SearchTutorsAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Tutor> objects;

    public SearchTutorsAdapter(Context context, ArrayList<Tutor> tutors) {
        ctx = context;
        objects = tutors;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.search_tutor_item, parent, false);
        }

        Tutor tutor = getProduct(position);

        ((TextView) view.findViewById(R.id.itemSearchFirstName)).setText(tutor.getFirstName());
        ((TextView) view.findViewById(R.id.itemSearchLastName)).setText(tutor.getLastName());
        ((TextView) view.findViewById(R.id.itemSearchItemsTypes)).setText(tutor.itemsToString());
        ((TextView) view.findViewById(R.id.itemSearchSubscribtionType)).setText(tutor.getSubscriptionType().toString());
        ((ImageView) view.findViewById(R.id.itemSearchAvatar)).setImageResource(R.drawable.anonim);
        return view;
    }

    // товар по позиции
    Tutor getProduct(int position) {
        return ((Tutor) getItem(position));
    }
}
