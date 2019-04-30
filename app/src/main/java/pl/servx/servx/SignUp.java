package pl.servx.servx;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import pl.servx.servx.Model.User;
import pl.servx.servx.Model.request;

import static android.util.Patterns.*;

public class SignUp extends Fragment {
    int counter = 0;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState){
        View rootView = inflater.inflate(R.layout.tabbed_signup, container, false);

        final MaterialEditText edtPhone, edtName, edtEmail, edtPassword, edtPasswordconf;
        Button btnSignUp;

            edtName = rootView.findViewById(R.id.edtName);
            edtPhone = rootView.findViewById(R.id.edtPhone);
            edtEmail = rootView.findViewById(R.id.edtEmail);
            edtPassword = rootView.findViewById(R.id.edtPassword);
            edtPasswordconf =  rootView.findViewById(R.id.edtPasswordconf);
            btnSignUp = rootView.findViewById(R.id.btnSignUp);



            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference table_user = database.getReference("User");
            final DatabaseReference table_user1 = database.getReference("requests");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ProgressDialog mDialog = new ProgressDialog(getActivity());
                    mDialog.setMessage("Please wait");
                    mDialog.show();

/*                    String emailInput = edtEmail.getText().toString().trim();

                    if(emailInput.isEmpty())
                    {
                        edtEmail.setError("Field is empty");
                    }

                    else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
                        edtEmail.setError("Email Address not valid");
                    }*/

                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {
                            //check if already user phone
                            if (dataSnapshot.child(edtPhone.getText().toString()).exists())
                            {
                                mDialog.dismiss();
                                if(counter == 0 )
                                {
                                    Toast.makeText(getActivity(), "User Already exists", Toast.LENGTH_LONG).show();
                                }
                            }

                            else
                            {
                                if(edtPassword.getText().toString().equals(edtPasswordconf.getText().toString())) {
                                    mDialog.dismiss();
                                    User user = new User(edtName.getText().toString(), edtPassword.getText().toString());
                                    table_user.child(edtPhone.getText().toString()).setValue(user);
                                    request req = new request();
                                    String x = "0";
                                    x= '"'+x+'"';

                                    table_user1.child(edtPhone.getText().toString()).child(x).setValue(req);
                                    Toast.makeText(getActivity(), "SignUp successul", Toast.LENGTH_LONG).show();
                                    counter = 1;
                                    Intent signin = new Intent(getActivity(), Tabbed_Main.class );
                                    startActivity(signin);
                                    getActivity().finish();
                                }
                                else{
                                    mDialog.dismiss();
                                    Toast.makeText(getActivity(),"make sure your passwords match", Toast.LENGTH_LONG).show();
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


        return rootView;
    }
}

/*
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
        final DatabaseReference table_user1 = database.getReference("requests");


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
                                request req = new request();

                                table_user1.child(edtPhone.getText().toString()).child("0").setValue(req);
                                Toast.makeText(SignUp.this, "SignUp successul", Toast.LENGTH_LONG).show();
                                counter = 1;
                                Intent signin = new Intent( SignUp.this, sign_in.class );
                                startActivity(signin);
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
*/
