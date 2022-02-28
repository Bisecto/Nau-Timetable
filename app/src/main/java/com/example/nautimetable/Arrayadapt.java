package com.example.nautimetable;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Arrayadapt extends ArrayAdapter {
    ArrayList<String> Time,Location,Course,mor;

    Context context;

    public Arrayadapt(@NonNull Context context, ArrayList<String> Time, ArrayList<String> Location, ArrayList<String> Course, ArrayList<String> mor) {
        super(context,R.layout.main,Time);
        this.Time = Time;
        this.Location = Location;
        this.Course = Course;
        this.mor = mor;
        this.context = context;
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // return super.getView(position, convertView, parent);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.main,parent,false);
        TextView no = view.findViewById(R.id.no);
        TextView text1 = view.findViewById(R.id.time_area);
        TextView text2 = view.findViewById(R.id.Course);
        TextView text3 = view.findViewById(R.id.Location);
        TextView text4 = view.findViewById(R.id.more);
        no.setText(String.valueOf(position));
        text1.setText(Location.get(position));
        text2.setText(Time.get(position));
        text3.setText(Course.get(position));
        //text4.setText(mor.get(position));
        return view;
    }
}
