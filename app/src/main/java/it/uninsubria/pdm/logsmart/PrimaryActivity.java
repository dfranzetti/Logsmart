package it.uninsubria.pdm.logsmart;

/**
 * Created by Denny on 15/12/2018.
 */

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
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class PrimaryActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

        Email = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.btnLogin);
        userRegistration = (TextView) findViewById(R.id.tvRegister);


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        Info.setText("Numero di tentativi rimasti: 5");

        if (user != null) {
            finish();
            startActivity(new Intent(PrimaryActivity.this, SecondActivity.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrimaryActivity.this, Prenota.class));
            }
        });
    }

    private void validate(String userName, String userPassword) {


        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(PrimaryActivity.this, "Login Effettuato", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    checkEmailVerification();


                } else {
                    Toast.makeText(PrimaryActivity.this, "Login Fallito", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("Numero di tentativi rimasti: " + counter);
                    if (counter == 0) {
                        Login.setEnabled(false);
                    }
                }
            }
        });
    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        Intent sendToLogout = new Intent(getApplicationContext(),
                SecondActivity.class);
        startActivity(sendToLogout);


                      /* if(emailflag){
            finish();
            startActivity(new Intent(PrimaryActivity.this, SecondActivity.class));
        }else{
            Toast.makeText(this, "Verifica la Mail", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
       }
       */

    }

    public void clickAccedi(View v) {
        switch (v.getId()) {
            case R.id.button5:
                Intent myIntent = new Intent();
                myIntent.setClassName("it.uninsubria.pdm.logsmart", "PrimaryActivity");
                startActivity(myIntent);
                break;
        }
    }
}




