package com.example.bwcha.finalprojectroughdraft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.bwcha.finalprojectroughdraft.CreateClassActivity.classRef;
import static com.example.bwcha.finalprojectroughdraft.CreateClassActivity.messages;

public class ProfessorActivity extends AppCompatActivity {

    List<String> messageList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        final Button viewQuestionButton = (Button)findViewById(R.id.viewQuestionButton);
        final TextView questionTextView = (TextView)findViewById(R.id.questionTextView);
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
                    else if(dataSnap.getValue().equals(Long.valueOf(0))) {
                        reds++;
                    }
                    else {

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

        messages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<DataSnapshot> snapShotList = new ArrayList<>();
                for (DataSnapshot dataSnap: dataSnapshot.getChildren()) {
                    snapShotList.add(dataSnap);
                }
                String question = snapShotList.get(snapShotList.size()-1).getValue().toString();
                messageList.add(question);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        viewQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (messageList.size() != 0) {
                    questionTextView.setText(messageList.get(0).toString());
                    messageList.remove(0);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "There are no questions",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    @Override
    public void onStop() {
        classRef.removeValue();
        super.onStop();
        finish();
    }

    @Override
    public void onDestroy() {
        classRef.removeValue();
        super.onDestroy();
        finish();
    }

}
