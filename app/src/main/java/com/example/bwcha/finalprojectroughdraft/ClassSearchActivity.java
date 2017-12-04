package com.example.bwcha.finalprojectroughdraft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import static com.example.bwcha.finalprojectroughdraft.MainActivity.firebaseDatabase;
import static com.example.bwcha.finalprojectroughdraft.MainActivity.root;

public class ClassSearchActivity extends AppCompatActivity {

    Button searchClassButton;
    EditText searchClassText;
    public static String studentName;
    public static String classRefName;
    public static DatabaseReference classSearched;
    public static DatabaseReference student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_search);
        searchClassButton = (Button)findViewById(R.id.searchClassButton);
        searchClassText = (EditText)findViewById(R.id.searchClassIdText);
        searchClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                root.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("class" + searchClassText.getText().toString())) {
                            classSearched = firebaseDatabase.getReference("class" + searchClassText.getText().toString());
                            classRefName = "class" + searchClassText.getText().toString();
                            student = classSearched.push();
                            studentName = student.getKey();
                            student.setValue(2);
                            Intent intent = new Intent(v.getContext(), StudentActivity.class);
                            startActivity(intent);
                            finish();

                        }else {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Class Id does not exist. Try again.",Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }

}
