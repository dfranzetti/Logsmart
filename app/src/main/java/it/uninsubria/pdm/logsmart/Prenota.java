package it.uninsubria.pdm.logsmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Denny on 27/12/2018.
 */

public class Prenota extends AppCompatActivity {

    private EditText userName, userPassword, userEmail;
    private Button regButton;
    private Button home;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;

    public void changeActivity(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {

                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                firebaseAuth.signOut();
                                Toast.makeText(Prenota.this, "Registrazione Effettuata", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(Prenota.this, SecondActivity.class));
                            } else {
                                Toast.makeText(Prenota.this, "Registrazione Fallita", Toast.LENGTH_SHORT).show();
                            }

                        }

                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Prenota.this, PrimaryActivity.class));
            }
        });
    }


    private void setupUIViews() {
        userName = (EditText) findViewById(R.id.etUserName);
        userPassword = (EditText) findViewById(R.id.etUserPassword);
        userEmail = (EditText) findViewById(R.id.etUserEmail);
        regButton = (Button) findViewById(R.id.btnRegister);
        userLogin = (TextView) findViewById(R.id.tvUserLogin);
    }

    private Boolean validate() {
        Boolean result = false;

        String nome = userName.getText().toString();
        String password = userPassword.getText().toString();
        String email = userEmail.getText().toString();

        if (nome.isEmpty() || password.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Per favore compila tutti i campi", Toast.LENGTH_SHORT).show();

        } else {
            result = true;

        }

        return result;
    }

    public void buttonClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                Intent myIntent = new Intent();
                myIntent.setClassName("it.uninsubria.pdm.logsmart", "Prenota");
                startActivity(myIntent);
                break;
        }
    }
}

