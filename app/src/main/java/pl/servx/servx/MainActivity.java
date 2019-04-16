package pl.servx.servx;

import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn, btnSignUp;

    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        logo = (ImageView)findViewById(R.id.logo);
        btnSignIn= (Button)findViewById(R.id.btnSignIn);

         btnSignUp= (Button)findViewById(R.id.btnSignUp);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent SignUp = new Intent( MainActivity.this, SignUp.class );
                Pair[] pairs = new Pair[2];
                        pairs[0] = new Pair<View, String>(logo,"logotrans");
                        pairs[1] = new Pair<View, String>(btnSignUp,"signuptrans");


                ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs );
                startActivity(SignUp, op.toBundle());

            }
        });

    btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign_in = new Intent( MainActivity.this, sign_in.class );

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(logo,"logotrans");
                pairs[1] = new Pair<View, String>(btnSignUp,"signintrans");


                ActivityOptions op = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs );
                startActivity(sign_in,op.toBundle());
            }
        });
    }
}
