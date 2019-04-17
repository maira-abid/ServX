package pl.servx.servx;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import pl.servx.servx.Model.User;

public class SignUp extends AppCompatActivity {

    int counter =0 ;
    int temp = 1;
    MaterialEditText edtPhone, edtName, edtEmail, edtPassword, edtPasswordconf;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        edtName = (MaterialEditText) findViewById(R.id.edtName);
        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);
        edtEmail = (MaterialEditText) findViewById(R.id.edtEmail);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        edtPasswordconf = (MaterialEditText) findViewById(R.id.edtPasswordconf);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please wait");
                mDialog.show();


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {
                        //check if already user phone
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            if(counter == 0 )
                             {

                                Toast.makeText(SignUp.this, "User Already exists", Toast.LENGTH_LONG).show();
                             }
                        }

                        else
                        {
                            if(edtPassword.getText().toString().equals(edtPasswordconf.getText().toString())) {
                                mDialog.dismiss();
                                User user = new User(edtName.getText().toString(), edtPassword.getText().toString());
                                table_user.child(edtPhone.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "SignUp successul", Toast.LENGTH_LONG).show();
                                counter = 1;
                                finish();
                            }
                            else{
                                mDialog.dismiss();
                                Toast.makeText(SignUp.this,"make sure your passwords match", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull  DatabaseError databaseError) {
                        mDialog.dismiss();


                    }
                });

            }
        });
    }
//    @Override
//    public void onBackPressed() {
//        Intent i= new Intent(SignUp.this, MainActivity.class);
//        startActivity(i);
//    }
}
