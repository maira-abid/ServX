package pl.servx.servx;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import pl.servx.servx.Model.car_list;

public class home extends AppCompatActivity{
    car_list helper;
    Button btnServices,btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Spinner sp = (Spinner) findViewById(R.id.spinner);
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference db= database.getReference("User");
        helper = new car_list(db);
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
                R.layout.simple_spinner_item, cars);
                adaptador.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adaptador);
    }
}
