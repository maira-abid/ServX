package pl.servx.servx;

<<<<<<< Updated upstream
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
=======
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
>>>>>>> Stashed changes

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

<<<<<<< Updated upstream
import java.util.ArrayList;
import java.util.Calendar;

import pl.servx.servx.Model.vehicle;

public class AddCarForm extends AppCompatActivity implements OnItemSelectedListener{
    Spinner spCarModel,spCarMake,spCarYear;
    Button ConfirmCar ;
    TextInputEditText textCarPlate;
=======
    int counter =0 ;
    EditText Cmake, Cmodel, Cyear, Cplate;
    Button btnConfirm;

>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_form);

<<<<<<< Updated upstream
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        spCarMake = (Spinner)findViewById(R.id.spCarMake);
        spCarModel = (Spinner)findViewById(R.id.spCarModel);
        spCarYear = (Spinner)findViewById(R.id.spCarYear);
        ConfirmCar = (Button)findViewById(R.id.ConfirmCar);
        textCarPlate = (TextInputEditText)findViewById(R.id.textCarPlate);

        spCarMake.setOnItemSelectedListener(this);

        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        years.add("Select Year");
        for (int i = 1996; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                R.layout.simple_spinner_item, years);
        adaptador.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

        spCarYear.setAdapter(adaptador);


        String[] makes = getResources().getStringArray(R.array.Make);
        ArrayAdapter<String> adaptador1 = new ArrayAdapter<String>(this,
                R.layout.simple_spinner_item, makes);
        adaptador1.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spCarMake.setAdapter(adaptador1);

        ConfirmCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vehicle newcar = new vehicle();
                newcar.vmake = String.valueOf(spCarMake.getSelectedItem());
                newcar.vmodel = String.valueOf(spCarModel.getSelectedItem());
                newcar.vyear = String.valueOf(spCarYear.getSelectedItem());
                table_user.child("03361424139").child("vehicle").child(textCarPlate.getText().toString()).setValue(newcar);




            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String make = String.valueOf(spCarMake.getSelectedItem());

        if(!make.equals("Select Make")){

            int resourceId = 0;
            try {
                resourceId = R.array.class.getField(make).getInt(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            String[] model = getResources().getStringArray(resourceId);

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                    R.layout.simple_spinner_item, model);
            adaptador.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            adaptador.notifyDataSetChanged();

            spCarModel.setAdapter(adaptador);
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
=======
        Button back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_back = new Intent(AddCarForm.this, home.class);
                startActivity(go_back);
            }
        });

        Cmake = (EditText) findViewById(R.id.Make_txt);
        Cmodel = (EditText) findViewById(R.id.Model_txt);
        Cyear = (EditText) findViewById(R.id.Year_txt);
        Cplate = (EditText) findViewById(R.id.CarPlate);
        btnConfirm = (Button) findViewById(R.id.ConfirmCar);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(AddCarForm.this);
                mDialog.setMessage("Please wait");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //check if already user phone
                        if (dataSnapshot.child(Cplate.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            if(counter == 0 )
                            {
                                Toast.makeText(AddCarForm.this, "Car Already Exists", Toast.LENGTH_LONG).show();
                            }
                        }
>>>>>>> Stashed changes

                        else
                        {
                                mDialog.dismiss();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        mDialog.dismiss();
                    }
                });
            }
        });
    }
}
