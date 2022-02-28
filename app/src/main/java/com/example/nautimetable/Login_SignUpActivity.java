9package com.example.nautimetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Login_SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView view1,view2;
    RelativeLayout signup,login_layout;
    Spinner spinner;
    EditText loginemail,loginpass;
    EditText lastname,firstname,sch_email,password,Confirm_password;
    int num;
    String selected;
    GoogleSignInOptions gso;
   // mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__sign_up);
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        loginemail=findViewById(R.id.login_email);
        loginpass=findViewById(R.id.Login_Password);
        lastname=findViewById(R.id.lastname);
        view1=findViewById(R.id.view1);
        view2=findViewById(R.id.view2);
        signup=findViewById(R.id.SignUp_Layout);
        login_layout=findViewById(R.id.Login_Layout);
        firstname=findViewById(R.id.firstname);
        progressBar=findViewById(R.id.progressbar);
        sch_email=findViewById(R.id.sch_email);
        password=findViewById(R.id.Password);
        spinner=findViewById(R.id.spinner);
        Confirm_password=findViewById(R.id.Confirm_Password);
        ArrayList<String> stringArrayList = new ArrayList<String>();
        for (int num=0; num<=130; num++){
            String pattern="000";
            DecimalFormat decimalFormat= new DecimalFormat(pattern);
            String output= decimalFormat.format(num);
            String regno= "2019514" +output;
            stringArrayList.add("2019514" +output);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,stringArrayList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selected = parent.getItemAtPosition(position).toString();

                    if (selected.trim().equals("2019514000")){

                        return;
                    } else {

                        Toast.makeText(Login_SignUpActivity.this, "u selected " + selected, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Toast.makeText(Login_SignUpActivity.this, "Select Your Registration number", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void signIn(View view) {

        String first= firstname.getText().toString().trim();
        String last= lastname.getText().toString().trim();
        String email= sch_email.getText().toString().trim();
        String pass= password.getText().toString().trim();
        String secl= selected;
        String confirm= Confirm_password.getText().toString();
        if (!first.equals("")) {
            if (!last.equals("")) {
                if (!email.equals("")) {
                    if (email.endsWith("@stu.unizik.edu.ng")) {
                        if (!secl.equals("2019514000")) {
                           if (!pass.equals("")){
                               if (pass.length()>6){
                                   if (confirm.equals(pass)){
                                       progressBar.setVisibility(View.VISIBLE);
                                       mAuth.createUserWithEmailAndPassword(email, pass)
                                            .addOnCompleteListener(task -> {
                                                if (task.isSuccessful()) {
                                                    User user = new User(first,
                                                            last,
                                                            email,
                                                            secl);
                                                    FirebaseDatabase.getInstance().getReference("users")
                                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                            .setValue(user).addOnCompleteListener(task1 -> {
                                                        if (task1.isSuccessful()){
                                                            Toast.makeText(Login_SignUpActivity.this, "Sucessful", Toast.LENGTH_SHORT).show();
                                                            progressBar.setVisibility(View.GONE);
                                                        } else {
                                                            Toast.makeText(Login_SignUpActivity.this, "Registration was not sucessful", Toast.LENGTH_SHORT).show();
                                                            progressBar.setVisibility(View.GONE);
                                                        }

                                                    });
                                                   // FirebaseUser user = mAuth.getCurrentUser();
                                                } else {
                                                    Toast.makeText(Login_SignUpActivity.this, "Registration was not sucessful", Toast.LENGTH_SHORT).show();
                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            });


                                   } else{
                                       Toast.makeText(this, "Confirm Your Password", Toast.LENGTH_LONG).show();
                                   }
                               }else {
                                   Toast.makeText(this, "Password Should be more than six Characters", Toast.LENGTH_SHORT).show();

                               }
                           } else {
                               Toast.makeText(this, "Password Required for login", Toast.LENGTH_LONG).show();
                           }

                        } else {
                            Toast.makeText(this, "Select Your Registration number", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        sch_email.setError("Please enter a valid School Email");
                    }

                } else {
                    sch_email.setError("Email Required");
                }


            } else {
                lastname.setError("Field required");
            }
        } else {
            firstname.setError("Field Required");
        }

    }

    public void Signin(View view) {
        String loemail= loginemail.getText().toString().trim();
        String lopass= loginpass.getText().toString().trim();
        if (!loemail.equals("")) {
            if (loemail.endsWith("@stu.unizik.edu.ng")) {
                if (lopass.length()>6){

                        progressBar.setVisibility(View.VISIBLE);
                        mAuth.signInWithEmailAndPassword(loemail,lopass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Intent i = new Intent(Login_SignUpActivity.this, MainActivity.class);
                                    startActivity(i);
                                } else{
                                    Toast.makeText(Login_SignUpActivity.this, "Failed to log In. Check Your credentials", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                }else {
                    Toast.makeText(this, "Password Should be more than six Characters", Toast.LENGTH_SHORT).show();

                }
                    } else {
                Toast.makeText(this, "Enter a valid Registered School Email", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "Email Needed", Toast.LENGTH_SHORT).show();
        }


    }

    public void goto_signUp(View view) {

        login_layout.setVisibility(View.GONE);
        signup.setVisibility(View.VISIBLE);
        view1.setVisibility(View.VISIBLE);

    }

    public void goto_login(View view) {
        view1.setVisibility(View.GONE);
        signup.setVisibility(View.GONE);
        login_layout.setVisibility(View.VISIBLE);
    }

    public void Forgot(View view) {
        Intent i = new Intent(this, ResetPassword.class);
        startActivity(i);
    }
}