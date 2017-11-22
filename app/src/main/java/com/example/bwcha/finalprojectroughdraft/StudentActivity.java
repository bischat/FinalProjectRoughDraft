package com.example.bwcha.finalprojectroughdraft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.example.bwcha.finalprojectroughdraft.ClassSearchActivity.classRefName;
import static com.example.bwcha.finalprojectroughdraft.ClassSearchActivity.student;
import static com.example.bwcha.finalprojectroughdraft.MainActivity.root;

public class StudentActivity extends AppCompatActivity {

    ToggleButton greenButton;
    ToggleButton yellowButton;
    ToggleButton redButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        greenButton = (ToggleButton) findViewById(R.id.greenButton);
        yellowButton = (ToggleButton)findViewById(R.id.yellowButton);
        redButton = (ToggleButton)findViewById(R.id.redButton);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(classRefName)) {
                    greenButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            greenButton.setChecked(true);
                            yellowButton.setChecked(false);
                            redButton.setChecked(false);
                            student.setValue(2);
                        }
                    });

                    yellowButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            yellowButton.setChecked(true);
                            greenButton.setChecked(false);
                            redButton.setChecked(false);
                            student.setValue(1);
                        }
                    });

                    redButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            redButton.setChecked(true);
                            greenButton.setChecked(false);
                            yellowButton.setChecked(false);
                            student.setValue(0);
                        }
                    });
                } else {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    @Override
    protected void onDestroy() {
        student.removeValue();
        super.onDestroy();
        finish();
    }

    @Override
    public void onStop() {
        student.removeValue();
        super.onStop();
        finish();
    }
}
