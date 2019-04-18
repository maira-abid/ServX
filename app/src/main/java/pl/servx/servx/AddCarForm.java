package pl.servx.servx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class AddCarForm extends AppCompatActivity implements OnItemSelectedListener{
    Spinner spCarModel,spCarMake,spCarYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_form);

        spCarMake = (Spinner)findViewById(R.id.spCarMake);
        spCarModel = (Spinner)findViewById(R.id.spCarModel);
        spCarYear = (Spinner)findViewById(R.id.spCarYear);

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

    }
}
