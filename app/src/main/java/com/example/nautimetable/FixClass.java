package com.example.nautimetable;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class FixClass extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    Button btn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Spinner Subjectspinner,Timespinner,Daysspinner,Hallspinner;
    String selected_Day,selected_Time,selected_Course,selected_Hall;

    public FixClass() {
        // Required empty public constructor
    }


    public static FixClass newInstance(String param1, String param2) {
        FixClass fragment = new FixClass();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fix_class, container, false);
        Subjectspinner = view.findViewById(R.id.subject_Spinner);
        String one [] = {"Select Course code", "CSC 201", "CSC 211", "CSC 221", "CSC 231", "CSC 261", "MAT 251", "MAT 201", "MAT 231"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(com.example.nautimetable.FixClass.super.getContext(), android.R.layout.simple_spinner_dropdown_item, one);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Subjectspinner.setAdapter(adapter);
        Subjectspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_Course = parent.getItemAtPosition(position).toString();
                if (selected_Course.trim().equals("Select Course code")) {

                    return;
                } else {

                   // Toast.makeText(com.example.nautimetable.FixClass.super.getContext(), "u selected " + selected_Course, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        Timespinner = view.findViewById(R.id.Time_Spinner);
        String two[]={"Select Time for Lecture","7Am-8Am","8Am-9Am","9Am-10Am","10Am-11Am","11Am-12Pm","12Pm-1Pm","1Pm-2Pm","2Pm-3Pm","3Pm-4Pm","4Pm-5Pm","5Pm-6Pm"};
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(com.example.nautimetable.FixClass.super.getContext(), android.R.layout.simple_spinner_dropdown_item, two);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Timespinner.setAdapter(adapt);
        Timespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_Time = parent.getItemAtPosition(position).toString();
                if (selected_Time.trim().equals("Select Time for Lecture")) {

                    return;
                } else {

                   // Toast.makeText(com.example.nautimetable.FixClass.super.getContext(), "u selected " + selected_Time, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        Daysspinner = view.findViewById(R.id.Days_Spinner);
        String three[]={"Select The Day for Lecture","Monday","Tuesday","Wenesday","Thursday","Friday"};
        ArrayAdapter<String> adap = new ArrayAdapter<String>(com.example.nautimetable.FixClass.super.getContext(), android.R.layout.simple_spinner_dropdown_item, three);
        adap.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Daysspinner.setAdapter(adap);
        Daysspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_Day = parent.getItemAtPosition(position).toString();
                if (selected_Day.trim().equals("Select The Day for Lecture")) {

                    return;
                } else {

                    //Toast.makeText(com.example.nautimetable.FixClass.super.getContext(), "u selected " + selected_Day, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        Hallspinner = view.findViewById(R.id.Hall_Spinner);
        String four[]={"Select Hall for Lecture","Hall 1","Hall 2","Hall 3","Hall 4","Hall 5","Hall 6","Hall 7","Hall 8","Hall 9","Hall 10"};
        ArrayAdapter<String> ada = new ArrayAdapter<String>(com.example.nautimetable.FixClass.super.getContext(), android.R.layout.simple_spinner_dropdown_item, four);
        ada.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Hallspinner.setAdapter(ada);
        Hallspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_Hall = parent.getItemAtPosition(position).toString();
                if (selected_Hall.trim().equals("Select Hall for Lecture")) {

                    return;
                } else {

                    //oast.makeText(com.example.nautimetable.FixClass.super.getContext(), "u selected " + selected_Hall, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        btn=view.findViewById(R.id.fix);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day_spinner=selected_Day;
                String hall_spinner=selected_Hall;
                String time_spinner=selected_Time;
                String course_spinner= selected_Course;
                if (!day_spinner.equals("Select The Day for Lecture")){
                    if (!hall_spinner.equals("Select Hall for Lecture")){
                        if (!time_spinner.equals("Select Time for Lecture")){
                            if (!course_spinner.equals("Sielect Course code")){
                               Timetab Timetable= new Timetab(day_spinner,time_spinner,course_spinner,hall_spinner);
                                if (day_spinner.equals("Monday")){
                                    switch (time_spinner) {
                                        case "7Am-8Am":
                                            db.collection("Monday").document("A").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "8Am-9Am":
                                            db.collection("Monday").document("B").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "9Am-10Am":
                                            db.collection("Monday").document("C").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "10Am-11Am":
                                            db.collection("Monday").document("D").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "11Am-12Pm":
                                            db.collection("Monday").document("E").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "12Pm-1Pm":
                                            db.collection("Monday").document("F").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "1Pm-2Pm":
                                            db.collection("Monday").document("G").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "2Pm-3Pm":
                                            db.collection("Monday").document("H").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "3Pm-4Pm":
                                            db.collection("Monday").document("I").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "4Pm-5Pm":
                                            db.collection("Monday").document("J").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "5Pm-6Pm":
                                            db.collection("Monday").document("K").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                    }

                                }
                                if (day_spinner.equals("Tuesday")){
                                    switch (time_spinner) {
                                        case "7Am-8Am":
                                            db.collection("Tuesday").document("7Am-8Am").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "8Am-9Am":
                                            db.collection("Tuesday").document("8Am-9Am").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "9Am-10Am":
                                            db.collection("Tuesday").document("9Am-10Am").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "10Am-11Am":
                                            db.collection("Tuesday").document("10Am-11Am").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "11Am-12Pm":
                                            db.collection("Tuesday").document("11Am-12Pm").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "12Pm-1Pm":
                                            db.collection("Tuesday").document("12Pm-1Pm").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "1Pm-2Pm":
                                            db.collection("Tuesday").document("1Pm-2Pm").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "2Pm-3Pm":
                                            db.collection("Tuesday").document("2Pm-3Pm").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "3Pm-4Pm":
                                            db.collection("Tuesday").document("3Pm-4Pm").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "4Pm-5Pm":
                                            db.collection("Tuesday").document("4Pm-5Pm").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                        case "5Pm-6Pm":
                                            db.collection("Tuesday").document("5Pm-6Pm").set(Timetable)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            break;
                                    }

                                }
                                {
                                    if (day_spinner.equals("Wenesday")) {
                                        switch (time_spinner) {
                                            case "7Am-8Am":
                                                db.collection("Wenesday").document("7Am-8Am").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "8Am-9Am":
                                                db.collection("Wenesday").document("8Am-9Am").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "9Am-10Am":
                                                db.collection("Wenesday").document("9Am-10Am").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "10Am-11Am":
                                                db.collection("Wenesday").document("10Am-11Am").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "11Am-12Pm":
                                                db.collection("Wenesday").document("11Am-12Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "12Pm-1Pm":
                                                db.collection("Wenesday").document("12Pm-1Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "1Pm-2Pm":
                                                db.collection("Wenesday").document("1Pm-2Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "2Pm-3Pm":
                                                db.collection("Wenesday").document("2Pm-3Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "3Pm-4Pm":
                                                db.collection("Wenesday").document("3Pm-4Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "4Pm-5Pm":
                                                db.collection("Wenesday").document("4Pm-5Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "5Pm-6Pm":
                                                db.collection("Wenesday").document("5Pm-6Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                        }
                                    }
                                }
                                {
                                    if (day_spinner.equals("Thursday")) {
                                        switch (time_spinner) {
                                            case "7Am-8Am":
                                                db.collection("Thursday").document("7Am-8Am").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "8Am-9Am":
                                                db.collection("Thursday").document("8Am-9Am").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "9Am-10Am":
                                                db.collection("Thursday").document("9Am-10Am").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "10Am-11Am":
                                                db.collection("Thursday").document("10Am-11Am").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "11Am-12Pm":
                                                db.collection("Thursday").document("11Am-12Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "12Pm-1Pm":
                                                db.collection("Thursday").document("12Pm-1Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "1Pm-2Pm":
                                                db.collection("Thursday").document("1Pm-2Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "2Pm-3Pm":
                                                db.collection("Thursday").document("2Pm-3Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "3Pm-4Pm":
                                                db.collection("Thursday").document("3Pm-4Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "4Pm-5Pm":
                                                db.collection("Thursday").document("4Pm-5Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "5Pm-6Pm":
                                                db.collection("Thursday").document("5Pm-6Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                        }
                                    }
                                    if (day_spinner.equals("Friday"))
                                    {
                                        switch (time_spinner) {
                                            case "7Am-8Am":
                                                db.collection("Friday").document("7Am-8Am").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "8Am-9Am":
                                                db.collection("Friday").document("8Am-9Am").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "9Am-10Am":
                                                db.collection("Friday").document("9Am-10Am").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "10Am-11Am":
                                                db.collection("Friday").document("10Am-11Am").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "11Am-12Pm":
                                                db.collection("Friday").document("11Am-12Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "12Pm-1Pm":
                                                db.collection("Friday").document("12Pm-1Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "1Pm-2Pm":
                                                db.collection("Friday").document("1Pm-2Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "2Pm-3Pm":
                                                db.collection("Friday").document("2Pm-3Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "3Pm-4Pm":
                                                db.collection("Friday").document("3Pm-4Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "4Pm-5Pm":
                                                db.collection("Friday").document("4Pm-5Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                            case "5Pm-6Pm":
                                                db.collection("Friday").document("5Pm-6Pm").set(Timetable)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(FixClass.super.getContext(), "sucesss", Toast.LENGTH_SHORT).show();

                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(FixClass.super.getContext(), " not a sucesss", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                break;
                                        }
                                    }
                                }
                            } else {
                                Toast.makeText(FixClass.super.getContext(), "Course code must be selected", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(FixClass.super.getContext(), "Time must be selected", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(FixClass.super.getContext(), "Hall must be selected", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(FixClass.super.getContext(), "A day must be selected", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(com.example.nautimetable.FixClass.super.getContext(), "", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
    public void Mondy(View view){

    }
}