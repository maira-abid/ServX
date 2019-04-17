package pl.servx.servx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Services extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Button back= (Button) findViewById(R.id.back_button);
        //final Class prev= getIntent().getSerializableExtra("extra");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_back= new Intent(Services.this, home.class );
                startActivity(go_back);
            }
        });



    }
}
