package com.example.nautimetable;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Mon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Mon extends Fragment {
    private FirebaseAuth mAuth;
    LayoutInflater inflater;
    ArrayList<String> more;
    Context context;
    Mon manday;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> Time,Location,Course,mor;
    ListView listView;
    CollectionReference collectionReference=db.collection("Monday");
    SwipeRefreshLayout swipeRefreshLayout;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Object Mon;


    public Mon() {
        // Required empty public constructor
    }


    public static Mon newInstance(String param1, String param2) {
        Mon fragment = new Mon();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        Time= new ArrayList<>();
        Location= new ArrayList<>();
        Course= new ArrayList<>();
        mor= new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_mon,container,false);
        listView= view.findViewById(R.id.listview);
        //swipeRefreshLayout=view.findViewById(R.id.ref);

        collectionReference.document("8Am-9Am").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("first","Sucess");

                if(documentSnapshot.exists()){
                    Log.d("sef","Sucess");
                 // Timetab Timetable=documentSnapshot.toObject(Timetab.class);
                 // String Day=Timetable.getDay();
                  //String Times=Timetable.getTime();
                  //String Cour=Timetable.getCourse();
                  //String Hall=Timetable.getHall();
                  //Log.d("message",Day+""+Times+""+Cours+""+Hall);
                  //Toast.makeText(com.example.nautimetable.Mon.super.getContext(), Day+""+Times+""+Cour+""+Hall, Toast.LENGTH_SHORT).show();


//                  Time.add(Times);
//
//
//                        Location.add(Hall);
//
//
//
//                        Course.add(Cours);
//
//                        Arrayadapt array = new Arrayadapt(com.example.nautimetable.Mon.super.getContext(), Course,Time,Location,mor);
//                        listView.setAdapter(array);
              } else {
                  Log.d("one","erorrrrrrrrrrr");

              }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("two","cdsyugdscy");

            }
        });
               DocumentReference doc;
                       doc= FirebaseFirestore.getInstance().collection("Monday").document("A");

               doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot dos=task.getResult();
                    if (dos.exists()){
                        String course_code= dos.getString("Course Code");
                        String Times=dos.getString("Time");
                        String Hall=dos.getString("Hall");
                        Time.add(Times);


                        Location.add(Hall);



                        Course.add(course_code);

                        Arrayadapt array = new Arrayadapt(com.example.nautimetable.Mon.super.getContext(), Course,Time,Location,mor);
                        listView.setAdapter(array);
                    }else {
                        Log.d("Documents","No data");
                    }

                }
            }
        });

                doc= FirebaseFirestore.getInstance().collection("Monday").document("B");
                doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot dos=task.getResult();
                            if (dos.exists()){
                                String course_code= dos.getString("Course Code");
                                String Times=dos.getString("Time");
                                String Hall=dos.getString("Hall");
                                Time.add(Times);


                                Location.add(Hall);



                                Course.add(course_code);

                                Arrayadapt array = new Arrayadapt(com.example.nautimetable.Mon.super.getContext(), Course,Time,Location,mor);
                                listView.setAdapter(array);
                            }else {
                                Log.d("Documents","No data");
                            }

                        }
                    }
                });


        doc= FirebaseFirestore.getInstance().collection("Monday").document("C");
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot dos=task.getResult();
                    if (dos.exists()){
                        String course_code= dos.getString("Course Code");
                        String Times=dos.getString("Time");
                        String Hall=dos.getString("Hall");
                        Time.add(Times);


                        Location.add(Hall);



                        Course.add(course_code);

                        Arrayadapt array = new Arrayadapt(com.example.nautimetable.Mon.super.getContext(), Course,Time,Location,mor);
                        listView.setAdapter(array);
                    }else {
                        Log.d("Documents","No data");
                    }

                }
            }
        });


        doc= FirebaseFirestore.getInstance().collection("Monday").document("D");
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot dos=task.getResult();
                    if (dos.exists()){
                        String course_code= dos.getString("Course Code");
                        String Times=dos.getString("Time");
                        String Hall=dos.getString("Hall");
                        Time.add(Times);


                        Location.add(Hall);



                        Course.add(course_code);

                        Arrayadapt array = new Arrayadapt(com.example.nautimetable.Mon.super.getContext(), Course,Time,Location,mor);
                        listView.setAdapter(array);
                    }else {
                        Log.d("Documents","No data");
                    }

                }
            }
        });



        doc= FirebaseFirestore.getInstance().collection("Monday").document("E");
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot dos=task.getResult();
                    if (dos.exists()){
                        String course_code= dos.getString("Course Code");
                        String Times=dos.getString("Time");
                        String Hall=dos.getString("Hall");
                        Time.add(Times);


                        Location.add(Hall);



                        Course.add(course_code);

                        Arrayadapt array = new Arrayadapt(com.example.nautimetable.Mon.super.getContext(), Course,Time,Location,mor);
                        listView.setAdapter(array);
                    }else {
                        Log.d("Documents","No data");
                    }

                }
            }
        });



        doc= FirebaseFirestore.getInstance().collection("Monday").document("F");
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot dos=task.getResult();
                    if (dos.exists()){
                        String course_code= dos.getString("Course Code");
                        String Times=dos.getString("Time");
                        String Hall=dos.getString("Hall");
                        Time.add(Times);


                        Location.add(Hall);



                        Course.add(course_code);

                        Arrayadapt array = new Arrayadapt(com.example.nautimetable.Mon.super.getContext(), Course,Time,Location,mor);
                        listView.setAdapter(array);
                    }else {
                        Log.d("Documents","No data");
                    }

                }
            }
        });
        doc= FirebaseFirestore.getInstance().collection("Monday").document("G");
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot dos=task.getResult();
                    if (dos.exists()){
                        String course_code= dos.getString("Course Code");
                        String Times=dos.getString("Time");
                        String Hall=dos.getString("Hall");
                        Time.add(Times);


                        Location.add(Hall);



                        Course.add(course_code);

                        Arrayadapt array = new Arrayadapt(com.example.nautimetable.Mon.super.getContext(), Course,Time,Location,mor);
                        listView.setAdapter(array);
                    }else {
                        Log.d("Documents","No data");
                    }

                }
            }
        });

        doc= FirebaseFirestore.getInstance().collection("Monday").document("H");
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot dos=task.getResult();
                    if (dos.exists()){
                        String course_code= dos.getString("Course Code");
                        String Times=dos.getString("Time");
                        String Hall=dos.getString("Hall");
                        Time.add(Times);


                        Location.add(Hall);



                        Course.add(course_code);

                        Arrayadapt array = new Arrayadapt(com.example.nautimetable.Mon.super.getContext(), Course,Time,Location,mor);
                        listView.setAdapter(array);
                    }else {
                        Log.d("Documents","No data");
                    }

                }
            }
        });


        doc= FirebaseFirestore.getInstance().collection("Monday").document("I");
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot dos=task.getResult();
                    if (dos.exists()){
                        String course_code= dos.getString("Course Code");
                        String Times=dos.getString("Time");
                        String Hall=dos.getString("Hall");
                        Time.add(Times);


                        Location.add(Hall);



                        Course.add(course_code);

                        Arrayadapt array = new Arrayadapt(com.example.nautimetable.Mon.super.getContext(), Course,Time,Location,mor);
                        listView.setAdapter(array);
                    }else {
                        Log.d("Documents","No data");
                    }

                }
            }
        });


        doc= FirebaseFirestore.getInstance().collection("Monday").document("J");
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot dos=task.getResult();
                    if (dos.exists()){
                        String course_code= dos.getString("Course Code");
                        String Times=dos.getString("Time");
                        String Hall=dos.getString("Hall");
                        Time.add(Times);


                        Location.add(Hall);



                        Course.add(course_code);

                        Arrayadapt array = new Arrayadapt(com.example.nautimetable.Mon.super.getContext(), Course,Time,Location,mor);
                        listView.setAdapter(array);
                    }else {
                        Log.d("Documents","No data");
                    }

                }
            }
        });


        doc= FirebaseFirestore.getInstance().collection("Monday").document("K");
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot dos=task.getResult();
                    if (dos.exists()){
                        String course_code= dos.getString("Course Code");
                        String Times=dos.getString("Time");
                        String Hall=dos.getString("Hall");
                        Time.add(Times);


                        Location.add(Hall);
                        Course.add(course_code);

                        Arrayadapt array = new Arrayadapt(com.example.nautimetable.Mon.super.getContext(), Course,Time,Location,mor);
                        listView.setAdapter(array);
                    }else {
                        Log.d("Documents","No data");
                    }

                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(com.example.nautimetable.Mon.super.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }
//    public boolean isNetworkAvailable(){
//
//        ConnectivityManager connectivityManager=(ConnectivityManager) this.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//                //this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
//        return networkInfo !=null;
//    }
}