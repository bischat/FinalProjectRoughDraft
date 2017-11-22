package com.example.bwcha.finalprojectroughdraft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import static com.example.bwcha.finalprojectroughdraft.CreateClassActivity.classRef;

public class ProfessorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        final TextView greenCountView = (TextView)findViewById(R.id.greenCountView);
        final TextView yellowCountView = (TextView)findViewById(R.id.yellowCountView);
        final TextView redCountView = (TextView)findViewById(R.id.redCountView);

        classRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int greens = 0;
                int yellows = 0;
                int reds = 0;
                for (DataSnapshot dataSnap: dataSnapshot.getChildren()) {
                    if (dataSnap.getValue().equals(Long.valueOf(2))) {
                        greens++;
                    }
                    else if(dataSnap.getValue().equals(Long.valueOf(1))) {
                        yellows++;
                    }
                    else {
                        reds++;
                    }
                }
                greenCountView.setText(greens+"");
                yellowCountView.setText(yellows+"");
                redCountView.setText(reds+"");
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        classRef.removeValue();
        super.onDestroy();
        finish();
    }

    @Override
    public void onStop() {
        classRef.removeValue();
        super.onStop();
        finish();
    }



}
