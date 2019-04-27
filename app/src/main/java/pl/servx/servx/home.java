package pl.servx.servx;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
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

import pl.servx.servx.Model.SharePref;
import pl.servx.servx.Model.car_list;

public class home extends AppCompatActivity{
    car_list helper;
    Button btnServices,btnHistory,btnAddCar, btnmaps;
    TextView stat_text;
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Intent intent = getIntent();
        final String user_name = intent.getStringExtra("extra");
        SharePref sharePref = new SharePref();


        sharePref.save(this,user_name);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
        stat_text= (TextView) findViewById(R.id.stat_text);
        Spinner sp = (Spinner) findViewById(R.id.spinner);
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference db= database.getReference("User");
        helper = new car_list(db);
        final ArrayList<String> cars =new ArrayList<>();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.child(user_name).child("vehicle").getChildren())
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

        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this,
                R.layout.simple_spinner_item, cars);
                adapt.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                sp.setAdapter(adapt);



        btnAddCar= (Button) findViewById(R.id.btnAddCar);
        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent form = new Intent( home.this, AddCarForm.class );
                startActivity(form);
            }
        });

        btnmaps = (Button)findViewById(R.id.btnmaps);

        btnmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent maps = new Intent(home.this, select_location.class);
                startActivity(maps);
            }
        });



        btnServices= (Button) findViewById(R.id.btnServices);
        btnServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent services = new Intent( home.this, services_tabbed.class );

                startActivity(services);
            }
        });

        btnHistory= (Button) findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent services = new Intent( home.this, service_history.class );

                startActivity(services);
            }
        });


    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
