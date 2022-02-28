package com.example.nautimetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FirebaseUser user;
    DatabaseReference reference;
    String uid;
    FloatingActionButton fab;
    SwipeRefreshLayout swipeRefreshLayout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= findViewById(R.id.toolb);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        navigationView=findViewById(R.id.navigation);
        fab=findViewById(R.id.Fab);
        drawerLayout=findViewById(R.id.drawer);

    View header= navigationView.getHeaderView(0);
        TextView txt= header.findViewById(R.id.UsersName);
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("users");
        uid=user.getUid();
       // Toast.makeText(this, uid+"", Toast.LENGTH_SHORT).show();
        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprof= snapshot.getValue(User.class);
                if (userprof!=null){
                    String userfirst= userprof.first;
                    String userlast= userprof.last;
                  //  Toast.makeText(MainActivity.this, userfirst +" "+ userlast, Toast.LENGTH_SHORT).show();
                    Log.d("doc",userfirst +" "+ userlast);
                    txt.setText(userfirst +" "+ userlast);

                } else {
                    Toast.makeText(MainActivity.this, "hmmmm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Something Occurred", Toast.LENGTH_SHORT).show();
            }
        });
        setSupportActionBar(toolbar);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);


        actionBar.show();

        if (uid.equals("4To9O2ohDUUju4qMBVmPnRpYJNn1")){
            fab.setVisibility(View.VISIBLE);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FixClass fixClass= new FixClass();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_con, fixClass, null);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mon:
                        menuItem.setChecked(true);
                        Mon manday= new Mon();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_con, manday, null);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.tues:
                        menuItem.setChecked(true);
                        Tues twosday= new Tues();
                        FragmentTransaction transactio = getSupportFragmentManager().beginTransaction();
                        transactio.replace(R.id.fragment_con, twosday, null);
                        transactio.addToBackStack(null);
                        transactio.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.wed:
                        menuItem.setChecked(true);
                        Wed wedday= new Wed();
                        FragmentTransaction transacti = getSupportFragmentManager().beginTransaction();
                        transacti.replace(R.id.fragment_con, wedday, null);
                        transacti.addToBackStack(null);
                        transacti.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.fri:
                        menuItem.setChecked(true);
                        Fri fryday= new Fri();
                        FragmentTransaction transact = getSupportFragmentManager().beginTransaction();
                        transact.replace(R.id.fragment_con, fryday, null);
                        transact.addToBackStack(null);
                        transact.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.thurs:
                        menuItem.setChecked(true);
                        Thurs thur= new Thurs();
                        FragmentTransaction transac = getSupportFragmentManager().beginTransaction();
                        transac.replace(R.id.fragment_con, thur, null);
                        transac.addToBackStack(null);
                        transac.commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
//                    case R.id.share:
//                        menuItem.setChecked(true);
//                        Toast.makeText(MainActivity.this, "With id share", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.send:
//                        menuItem.setChecked(true);
//                        Toast.makeText(MainActivity.this, "With id send", Toast.LENGTH_SHORT).show();
//                        return true;
                }

                return false;
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

             case R.id.logout:
                 FirebaseAuth.getInstance().signOut();
                 startActivity(new Intent(MainActivity.this,Login_SignUpActivity.class));
                 return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setIcon(R.drawable.ic_baseline_warning_24);
        dialog.setTitle("Quit?");
        dialog.setMessage("are you sure you want to go Quit");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        final AlertDialog alert = dialog.create();
        alert.show();
    }

}