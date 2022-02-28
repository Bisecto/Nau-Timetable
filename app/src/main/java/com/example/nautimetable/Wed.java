package com.example.nautimetable;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Wed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Wed extends Fragment {
    LayoutInflater inflater;
    ArrayList<String> more;
    Context context;
    Mon manday;
    ArrayList<String> Time,Location,Course,mor;
    ListView listView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Wed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Wed.
     */
    // TODO: Rename and change types and number of parameters
    public static Wed newInstance(String param1, String param2) {
        Wed fragment = new Wed();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Time= new ArrayList<>();
        Location= new ArrayList<>();
        Course= new ArrayList<>();
        mor= new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_mon,container,false);
        listView= view.findViewById(R.id.listview);
        //listView= (listView) findViewById(R.id.listview);
        Time.add("7AM-8AM");
        Time.add("8AM-9AM");
        Time.add("9AM-10AM");
        Time.add("10AM-11AM");
        Time.add("11AM-12APM");
        Time.add("12PM-1PM");
        Time.add("1PM-2PM");
        Time.add("2PM-3PM");
        Time.add("3PMAM-4PM");
        Time.add("4PM-5PMM");
        Time.add("5PM-6PM");

        Location.add("Empty");
        Location.add("Empty");
        Location.add("Empty");
        Location.add("Empty");
        Location.add("Empty");
        Location.add("Empty");
        Location.add("Empty");
        Location.add("Empty");
        Location.add("Empty");
        Location.add("Empty");
        Location.add("Empty");


        Course.add("Subject");
        Course.add("Subject");
        Course.add("Subject");
        Course.add("Subject");
        Course.add("Subject");
        Course.add("Subject");
        Course.add("Subject");
        Course.add("Subject");
        Course.add("Subject");
        Course.add("Subject");
        Course.add("Subject");

        Arrayadapt array = new Arrayadapt(com.example.nautimetable.Wed.super.getContext(), Time,Location,Course,mor);
        listView.setAdapter(array);
        return view;
    }
}