package pl.servx.servx;

//import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pl.servx.servx.Model.User;

public class sign_in extends Fragment{
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState){
        View rootView = inflater.inflate(R.layout.tabbed_login, container, false);

        final EditText edtphone, edtpass;
        Button btnSignIn;

        edtpass = rootView.findViewById(R.id.edtPassword);
        edtphone = rootView.findViewById(R.id.edtPhone);
        btnSignIn = rootView.findViewById(R.id.btnSignIn);

        //init database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(getActivity());

                final String passs = edtpass.getText().toString().trim();
                final String num = edtphone.getText().toString().trim();

                if(num.isEmpty()) {
                    edtphone.setError("Field is empty");
                }

                if(passs.isEmpty()) {
                    edtpass.setError("Field is empty");
                }

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //user info
                        mDialog.dismiss();

                        if (num.isEmpty() || passs.isEmpty()) {
                            Toast.makeText(getActivity(), "Can Not Log-in With Empty Field", Toast.LENGTH_LONG).show();
                        }
                        else {
                            mDialog.setMessage("Please Wait");
                            mDialog.show();

                            if (dataSnapshot.child((edtphone.getText().toString())).exists()) {


                                User user = dataSnapshot.child(edtphone.getText().toString()).getValue(User.class);

                                if (user.getPassword().equals(edtpass.getText().toString())) {
                                    Toast.makeText(getActivity(), "Sign In Successful", Toast.LENGTH_LONG).show();

                                    Intent home = new Intent(getActivity(), home.class);
                                    home.putExtra("extra", edtphone.getText().toString());
                                    home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                    startActivity(home);
                                } else {
                                    Toast.makeText(getActivity(), "Sign-in Failed", Toast.LENGTH_LONG).show();
                                }

                            } else {
                                Toast.makeText(getActivity(), "This user Does Not Exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        return rootView;
    }

}

/*
public class sign_in extends AppCompatActivity {
    EditText edtphone, edtpass;
    Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtpass = (MaterialEditText)findViewById(R.id.edtPassword);
        edtphone = (MaterialEditText)findViewById(R.id.edtPhone);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);

        //init database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(sign_in.this);
                mDialog.setMessage("Please Wait");
                        mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //user info
                        mDialog.dismiss();

                        if (dataSnapshot.child((edtphone.getText().toString())).exists()) {


                            User user = dataSnapshot.child(edtphone.getText().toString()).getValue(User.class);

                            if (user.getPassword().equals(edtpass.getText().toString())) {
                                Toast.makeText(sign_in.this, "sign in successful", Toast.LENGTH_LONG).show();

                                Intent home = new Intent( sign_in.this, home.class );
                                home.putExtra("extra", edtphone.getText().toString());
                                home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                startActivity(home);
                            }
                            else {
                                Toast.makeText(sign_in.this, "sign-in failed", Toast.LENGTH_LONG).show();
                            }

                        }
                        else{
                            Toast.makeText(sign_in.this,"user does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
//    @Override
//    public void onBackPressed() {
//        Intent i= new Intent(sign_in.this, MainActivity.class);
//        startActivity(i);
//    }
}
*/
