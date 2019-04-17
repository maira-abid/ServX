package pl.servx.servx;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


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
    TextView stat_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        stat_text= (TextView) findViewById(R.id.stat_text);
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

        btnServices= (Button) findViewById(R.id.btnServices);
        btnServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent services = new Intent( home.this, services_tabbed.class );
                services.putExtra("extra", home.class);
                startActivity(services);
            }
        });
    }
    @Override
    public void onBackPressed() {
        //Intent intent = new Intent(Intent.ACTION_MAIN);
        //intent.addCategory(Intent.CATEGORY_HOME);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();
        super.onBackPressed();
        //startActivity(intent);
//        Intent i = new Intent(this, SplashScreen.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(i);
//        finish();

    }
}
