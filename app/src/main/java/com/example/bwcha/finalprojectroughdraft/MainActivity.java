package com.example.bwcha.finalprojectroughdraft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button studentButton;
    Button professorButton;

    public static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public static DatabaseReference root = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentButton = (Button)findViewById(R.id.studentButton);
        professorButton = (Button)findViewById(R.id.professorButton);

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ClassSearchActivity.class);
                startActivity(intent);
            }
        });

        professorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateClassActivity.class);
                startActivity(intent);
            }
        });
    }
}
