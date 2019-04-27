package pl.servx.servx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import pl.servx.servx.Model.cart_data;

public class Cart extends AppCompatActivity {

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
            String key = (String)mapElement.getKey();

            String value = (String)mapElement.getValue();
            String msg,cost;
            int comma= value.indexOf(",");
            msg= value.substring(0,comma);
            cost=value.substring(comma+1,value.length());
            texts.get(i).setText(msg);
            costs.get(i).setText(cost);
            i++;

        }


//        if (cart_data.OilChange!=""){
//        OC.setText("Oil Change: "+cart_data.OilChange
//        +" Package");
//        cost1.setText(cart_data.costOil+" Rs");
//        }
//
//
//        if (cart_data.CarWash!=""){
//        CW.setText("Car Wash "+cart_data.CarWash+ " Package");
//        cost2.setText(cart_data.costWash+" Rs");}

        tc_num.setText((cart_data.costWash+cart_data.costOil)+" Rs");







    }
}
