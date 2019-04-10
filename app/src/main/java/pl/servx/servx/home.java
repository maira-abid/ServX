package pl.servx.servx;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import pl.servx.servx.Model.firebasehelper;

public class home extends AppCompatActivity{
    firebasehelper helper;
    Button btnServices,btnHistory;
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sp= (Spinner) findViewById(R.id.spinner);
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference db= database.getReference("User");
        helper=new firebasehelper(db);
        final ArrayList<String> cars =new ArrayList<>();


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.child("03361424139").child("vehicle").getChildren())
                {
                    String name= String.valueOf(ds.getKey());
                    cars.add(name);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cars.add("Select Car");

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                R.layout.spinner_layout, cars);adaptador.setDropDownViewResource(R.layout.spinner_item);

        sp.setAdapter(adaptador);


    }




}
