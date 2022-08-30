package com.example.biljeke;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class zaboravljenalozinka extends AppCompatActivity {

    private EditText mzaboravljenalozinka;
    private Button moporavaklozinke;
    private TextView mvratisenaprijavu;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaboravljenalozinka);

        getSupportActionBar().hide();

        mzaboravljenalozinka=findViewById(R.id.forgotpassword);
        moporavaklozinke=findViewById(R.id.passwordrecoverbutton);
        mvratisenaprijavu=findViewById(R.id.gobacktologin);

        firebaseAuth = FirebaseAuth.getInstance();

        mvratisenaprijavu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(zaboravljenalozinka.this, MainActivity.class);
                startActivity(intent);

            }
        });

        moporavaklozinke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail= mzaboravljenalozinka.getText().toString().trim();
                if (mail.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Unesite Vašu e - mail adresu", Toast.LENGTH_SHORT).show();
                }

                else
            {

                firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"E - mail poslan, možete oporaviti Vaš račun",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(zaboravljenalozinka.this,MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Pogrešan e - mail",Toast.LENGTH_SHORT).show();
                        }


                    }
                });

            }
        }
    });


}
}




