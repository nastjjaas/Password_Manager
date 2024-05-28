package com.example.passwordmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText emailTxt,passTxt;
    Button loginbtn;
    TextView signup;

    public usersDB mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        emailTxt=findViewById(R.id.emailTxt);
        passTxt=findViewById(R.id.passTxt);
        loginbtn=findViewById(R.id.loginbtn);
        signup=findViewById(R.id.signup);
        //Objekt von database und open it
        mydb = new usersDB(this);
        mydb.open();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String uname = emailTxt.getText().toString();
                String pass = passTxt.getText().toString();
                if (!uname.isEmpty() && !pass.isEmpty()) {
                    if (mydb.verifyUser(uname,pass)) {
                        Intent intent = new Intent(MainActivity.this, mainsite.class);
                        intent.putExtra("username",uname);

                        startActivity(intent);
                        finish();


                        mydb.close();

                    } else {
                        Toast.makeText(MainActivity.this, "Falsche Eingaben", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });

    }
}