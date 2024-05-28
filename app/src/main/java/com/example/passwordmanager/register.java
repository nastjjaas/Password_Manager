package com.example.passwordmanager;

import android.content.Context;
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

public class register extends AppCompatActivity {

    EditText emailTxt,passTxt;
    Button registerbtn;
    TextView login;
    Context con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        con=this;
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        emailTxt=findViewById(R.id.emailNameTxt);
        passTxt=findViewById(R.id.passTxt);
        login=findViewById(R.id.login);
        registerbtn=findViewById(R.id.registerbtn);
        usersDB db =new usersDB(con);
        db.open();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password=passTxt.getText().toString();
                String useremail=emailTxt.getText().toString();

                if(!db.usernameExists(useremail)){
                    db.addNewUser(useremail,password);

                    if(db.verifyUser(useremail,password)){
                        Toast.makeText(con, "Account created", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(register.this, mainsite.class);
                        intent.putExtra("Email",useremail);
                        startActivity(intent);
                        db.close();
                        finish();
                    }
                }else
                {
                    Toast.makeText(con, "username already exists", Toast.LENGTH_SHORT).show();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(register.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}