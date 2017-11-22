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

public class CreateClassActivity extends AppCompatActivity {

    Button createClassButton;
    EditText createClassText;
    public static DatabaseReference classRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        createClassText = (EditText)findViewById(R.id.createClassText);
        createClassButton = (Button)findViewById(R.id.createClassButton);

        createClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                root.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("class" + createClassText.getText().toString())) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Class Id already exists. Try again.",Toast.LENGTH_LONG);
                            toast.show();

                        }else {
                            classRef = firebaseDatabase.getReference
                                    ("class" + createClassText.getText().toString());
                            classRef.setValue(createClassText.getText().toString());

                            Intent intent = new Intent(v.getContext(), ProfessorActivity.class);
                            startActivity(intent);
                            finish();
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
