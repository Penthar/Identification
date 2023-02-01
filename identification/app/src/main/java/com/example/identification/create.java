package com.example.identification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class create extends AppCompatActivity implements View.OnClickListener {
    EditText usernamebox;
    EditText passwordbox;
    EditText confirmpass;
    EditText agebox;
    EditText mailbox;
    Button createbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Intent intent = getIntent();
        usernamebox = findViewById(R.id.usernamebox);
        usernamebox.setText(intent.getExtras().getString("username", ""));
        passwordbox = findViewById(R.id.passwordbox);
        passwordbox.setText(intent.getExtras().getString("password", ""));
        confirmpass = findViewById(R.id.passwordconfirm);
        agebox = findViewById(R.id.agebox);
        mailbox = findViewById(R.id.mailbox);
        createbutton =findViewById(R.id.createbutton);
        createbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        DBHelper db = new DBHelper(this);
        if(!db.checkNameTaken(usernamebox.getText().toString()))
            Toast.makeText(this, "USERNAME ALREADY TAKEN", Toast.LENGTH_SHORT).show();
        else if (!passwordbox.getText().toString().equals(confirmpass.getText().toString()))
            Toast.makeText(this, "PASSWORD AND CONFIRM PASSWORD NOT SAME", Toast.LENGTH_SHORT).show();
        else{
            db.insertNewUser(new User(usernamebox.getText().toString(), passwordbox.getText().toString(), Integer.parseInt(agebox.getText().toString()), mailbox.getText().toString()));
            Toast.makeText(this, "NEW ACCOUNT CREATED!", Toast.LENGTH_SHORT).show();
        }
    }
}