package com.example.tutors.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.tutors.Models.ItemsTypes;
import com.example.tutors.Models.Tutor;
import com.example.tutors.R;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchTutorsAdapter extends BaseAdapter implements Filterable {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Tutor> tutors;
    private ItemFilter itemFilter;
    private ArrayList<Tutor> filteredData;

    public SearchTutorsAdapter(Context context, Query tutorsRef) {
        ctx = context;
        this.tutors = new ArrayList<>();
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemFilter = new ItemFilter();
        filteredData = this.tutors;

        tutorsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot tutorSnapshots: snapshot.getChildren()) {
                    tutors.add(tutorSnapshots.getValue(Tutor.class));
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
        return filteredData.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Tutor getTutor(int position) { return tutors.get(position); }

    public void add(Tutor tutor)
    {
        filteredData.add(tutor);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.search_tutor_item, parent, false);
        }

        Tutor tutor = getTutors(position);

        ((TextView) view.findViewById(R.id.itemSearchFirstName)).setText(tutor.getFirstName());
        ((TextView) view.findViewById(R.id.itemSearchLastName)).setText(tutor.getLastName());
        ((TextView) view.findViewById(R.id.itemSearchItemsTypes)).setText(tutor.getStringItems());
        ((TextView) view.findViewById(R.id.itemSearchSubscribtionType)).setText(tutor.getSubscriptionType().toString());
        ((TextView) view.findViewById(R.id.itemSearchRaiting)).setText(tutor.getStringRating());
        ((ImageView) view.findViewById(R.id.itemSearchAvatar)).setImageResource(R.drawable.anonim);
        return view;
    }

    Tutor getTutors(int position) {
        return ((Tutor) getItem(position));
    }

    @Override
    public Filter getFilter() {
        return this.itemFilter;
    }

    private class ItemFilter extends Filter {
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults result = new FilterResults();
            if(constraint == ItemsTypes.ALL.toString()){
                result.values = tutors;
                result.count = tutors.size();
            }
            else{
                ArrayList<Tutor> filteredList = new ArrayList<>();
                for(Tutor tutor: tutors){
                    if(tutor.getItems().contains(ItemsTypes.valueOf(constraint.toString().replace(' ', '_'))))
                    {
                        filteredList.add(tutor);
                    }
                }
                result.values = filteredList;
                result.count = filteredList.size();
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<Tutor>) results.values;
            notifyDataSetChanged();
        }
    }
}
