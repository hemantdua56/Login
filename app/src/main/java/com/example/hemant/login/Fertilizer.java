package com.example.hemant.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Fertilizer extends AppCompatActivity {


    DatabaseReference rootRef;
    TextView demoValue,demoTemp;
    Button r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizer);

        demoValue = findViewById(R.id.tvValue);
        demoTemp = findViewById(R.id.tvValue1);
        r= findViewById(R.id.button2);


        //database reference pointing to root of database
        rootRef = (DatabaseReference) FirebaseDatabase.getInstance().getReference();



        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query lastQuery = rootRef.child("sensor/dht/humidity").orderByKey().limitToLast(1);
                Query lastQuery1 = rootRef.child("sensor/dht/temperature").orderByKey().limitToLast(1);

                lastQuery1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        String name1=dataSnapshot.getValue().toString();
                        String final_val1=name1.substring(22,24);

                        demoTemp.setText("Current Temperature:"+final_val1 +"C");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                //database reference pointing to demo node
                lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String name=dataSnapshot.getValue().toString();
                        String final_val=name.substring(22,24);
                        demoValue.setText("Current Humidity:     "+final_val +"%");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });







    }
}
