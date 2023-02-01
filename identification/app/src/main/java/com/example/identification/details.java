package com.example.identification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class details extends AppCompatActivity implements View.OnClickListener {
    String name;
    Button changeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        name = intent.getExtras().getString("username", "error");
        DBHelper db = new DBHelper(this);
        User currUser = db.getUserFromName(name);
        TextView nametext = findViewById(R.id.nametext);
        nametext.setText(currUser.getUsername());
        TextView passtext = findViewById(R.id.passtext);
        passtext.setText(currUser.getPassword());
        TextView agetext = findViewById(R.id.agetext);
        agetext.setText(String.valueOf(currUser.getAge()));
        TextView mailtext = findViewById(R.id.mailtext);
        mailtext.setText(currUser.getMail());
        changeButton = findViewById(R.id.changebutton);
        changeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.changebutton){
            Intent intent = new Intent(details.this, UpdateActivity.class);
            intent.putExtra("username", name);
            startActivity(intent);
        }////logout
    }
}