package pl.servx.servx;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import java.util.ArrayList;
import java.util.Map;

import pl.servx.servx.Model.cart_data;

public class Cart extends AppCompatActivity {
    TextView date, time;
    String am_pm;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Button back_button= (Button) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent services= new Intent(Cart.this, services_tabbed.class);
                startActivity(services);
                finish();
            }
        });

        Button gotomap= (Button) findViewById(R.id.map_btn);
        gotomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent maps= new Intent(Cart.this, select_location.class);
                startActivity(maps);
                finish();
            }
        });

        date = (TextView) findViewById(R.id.date_btn);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Cart.this,
                        android.R.style.Theme_Holo_Dialog,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 10000);
                datePickerDialog.show();
            }
        });

        time = (TextView) findViewById(R.id.time_btn);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(Cart.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                                if(selectedHour >= 12) {
                                    am_pm = "pm";
                                }

                                if (selectedHour < 12) {
                                    am_pm = "am";
                                }
                                time.setText(selectedHour + ":" + selectedMinute + " " + am_pm);
                            }
                }, hour, minute,false);
                timePickerDialog.show();

            }
        });

        TextView cost1= (TextView) findViewById(R.id.cost1);
        TextView cost2= (TextView) findViewById(R.id.cost2);
        TextView total_cost= (TextView) findViewById(R.id.total_cost);
        TextView tc_num= (TextView) findViewById(R.id.tc_num);

        TextView OC= (TextView) findViewById(R.id.pack1);
        TextView CW= (TextView) findViewById(R.id.pack2);
        OC.setText(""); CW.setText(""); cost1.setText(""); cost2.setText("");
        ArrayList<TextView> texts= new ArrayList<>();
        ArrayList<TextView> costs= new ArrayList<>();
        texts.add(OC); texts.add(CW);
        costs.add(cost1); costs.add(cost2);
        int i=0;
        for (Map.Entry mapElement : cart_data.dict.entrySet()) {
            String key = (String) mapElement.getKey();

            String value = (String) mapElement.getValue();
            String msg, cost;
            int comma = value.indexOf(",");
            msg = value.substring(0, comma);
            cost = value.substring(comma + 1, value.length());
            texts.get(i).setText(msg);
            costs.get(i).setText(cost);
            i++;
        }

        tc_num.setText((cart_data.costWash+cart_data.costOil)+" Rs");

    }
}