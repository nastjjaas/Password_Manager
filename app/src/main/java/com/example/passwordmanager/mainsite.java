package com.example.passwordmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class mainsite extends AppCompatActivity {

    TextView userDisplay;
    Button addTxt, passwordstxt;
    usersDB mydb;

    String myUser; //muss hier sein sonst, kann es ober nicht gelesen werden

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainsite);

        userDisplay=findViewById(R.id.userDisplay);
        passwordstxt=findViewById(R.id.passwordstxt);
        addTxt=findViewById(R.id.addTxt);
        Username();
        mydb = new usersDB(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mainsite.this, addpasswords.class);
                intent.putExtra("email", myUser);  //macht ein schlüssel "email" und wert MyUser
                startActivity(intent);
            }
        });
    }

    public void Username()
    {
        Intent intent=getIntent();          //wird gestartet und gibt Objekt zurück, intent wird von registerklasse genommen
        if(intent!=null)
        {
            myUser=intent.getStringExtra("email");  //nimmt den string "email" als schlüssel
            if(myUser!=null)
            {

                userDisplay.setText(myUser);   //sonst steht myuser

            }
        }
    }
}