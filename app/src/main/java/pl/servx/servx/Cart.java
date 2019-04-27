package pl.servx.servx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                Intent home= new Intent(Cart.this, home.class);
                startActivity(home);
                finish();
            }
        });

        TextView test= (TextView) findViewById(R.id.pack1);
        test.setText(cart_data.OilChange);
        TextView test2= (TextView) findViewById(R.id.pack2);
        test2.setText(cart_data.CarWash);

    }
}
