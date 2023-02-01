package com.example.identification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    String name;
    EditText nametext;
    EditText passtext;
    EditText agetext;
    EditText mailtext;
    EditText confirmpass;
    Button updateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        name = intent.getExtras().getString("username", "error");
        DBHelper db = new DBHelper(this);
        User currUser = db.getUserFromName(name);
        nametext = findViewById(R.id.usernamebox);
        nametext.setText(currUser.getUsername());
        passtext = findViewById(R.id.passwordbox);
        passtext.setText(currUser.getPassword());
        agetext = findViewById(R.id.agebox);
        agetext.setText(String.valueOf(currUser.getAge()));
        mailtext = findViewById(R.id.mailbox);
        mailtext.setText(currUser.getMail());
        confirmpass = findViewById(R.id.passwordconfirm);
        confirmpass.setText(currUser.getPassword());
        updateButton=findViewById(R.id.updatebutton);
        updateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        DBHelper db = new DBHelper(this);
        if (passtext.getText().toString().equals(confirmpass.getText().toString())) {
            if (db.checkNameTaken(nametext.getText().toString()) || name.equals(nametext.getText().toString())) {
                db.updateUser(new User(nametext.getText().toString(), passtext.getText().toString(), Integer.parseInt(agetext.getText().toString()), mailtext.getText().toString()), name);
            } else
                Toast.makeText(this, "USERNAME ALREADY TAKEN", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "PASSWORD AND CONFIRM PASSWORD NOT SAME", Toast.LENGTH_SHORT).show();
    }
}