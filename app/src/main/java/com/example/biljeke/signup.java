package com.example.biljeke;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {

    private EditText msignupemail, msignuplozinka;
    private RelativeLayout mprijava;
    private TextView mgotologin;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();

        msignupemail = findViewById(R.id.signupemail);
        msignuplozinka = findViewById(R.id.signuppassword);
        mprijava = findViewById(R.id.signup);
        mgotologin = findViewById(R.id.gotologin);

        firebaseAuth = FirebaseAuth.getInstance();




        mgotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mprijava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = msignupemail.getText().toString().trim();
                String lozinka = msignuplozinka.getText().toString().trim();

                if (mail.isEmpty() || lozinka.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Sva su polja potrebna", Toast.LENGTH_SHORT).show();
                }
                else if (lozinka.length() < 7)
                {
                    Toast.makeText(getApplicationContext(), "Lozinka treba imati vi??e od 7 znakova", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    firebaseAuth.createUserWithEmailAndPassword(mail, lozinka).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Registracija uspje??na!", Toast.LENGTH_SHORT).show();
                                sendEmailVerification();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Registracija neuspje??na!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
    }
                    private void sendEmailVerification()
                    {
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        if (firebaseUser != null)
                        {
                            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getApplicationContext(), "Verifikacija e - maila je poslana, prijavite se ponovno!", Toast.LENGTH_SHORT).show();
                                    firebaseAuth.signOut();
                                    finish();
                                    startActivity(new Intent (signup.this, MainActivity.class));
                                }
                            });

                        }

                        else

                        {
                            Toast.makeText(getApplicationContext(), "Neuspje??no poslana verifikacija e-maila!", Toast.LENGTH_SHORT).show();

                        }
                    }
}
