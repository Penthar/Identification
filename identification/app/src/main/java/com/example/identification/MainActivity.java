package com.example.identification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button loginbutton;
    Button createbutton;
    Button displaybutton;
    EditText usernameinput;
    EditText passwordinput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginbutton = findViewById(R.id.loginbutton);
        createbutton = findViewById(R.id.createbutton);
        usernameinput = findViewById(R.id.usernamebox);
        passwordinput = findViewById(R.id.passwordbox);
        loginbutton.setOnClickListener(this);
        createbutton.setOnClickListener(this);
        displaybutton = findViewById(R.id.displaybutton);
        displaybutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.createbutton){
            Intent intent = new Intent(MainActivity.this, create.class);
            intent.putExtra("username", usernameinput.getText().toString());
            intent.putExtra("password", passwordinput.getText().toString());
            startActivity(intent);
        }
        else if (view.getId() == R.id.displaybutton){
            Intent intent = new Intent(MainActivity.this, DisplayActivity2.class);
            startActivity(intent);
        }
        else{
            DBHelper db = new DBHelper(this);
            if(db.userIdentification(usernameinput.getText().toString(), passwordinput.getText().toString())) {
                Intent intent = new Intent(MainActivity.this, details.class);
                intent.putExtra("username", usernameinput.getText().toString());
                startActivity(intent);
            }
            else
                Toast.makeText(this, "USERNAME OR PASSWORD ARE WRONG", Toast.LENGTH_SHORT).show();
        }
    }
}