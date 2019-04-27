package pl.servx.servx;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class serv_frag1 extends Fragment {







    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_serv_frag1, container, false);
        Button btn1= (Button) rootView.findViewById(R.id.btnP1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home= new Intent(getActivity(), home.class);
                startActivity(home);
            }
        });

        Button gotocart = (Button) rootView.findViewById(R.id.btn_cart);
        gotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cart= new Intent(getActivity(), Cart.class);
                startActivity(cart);
            }
        });

        final TextView p_info= (TextView) rootView.findViewById(R.id.p_info);
        final TextView p_info2= (TextView) rootView.findViewById(R.id.p_info2);
        final TextView p_info3= (TextView) rootView.findViewById(R.id.p_info3);
        FirebaseDatabase database = FirebaseDatabase.getInstance();



//        ArrayList<String> gold_list= getArguments().getStringArrayList("list1");
//        ArrayList<String> silver_list= getArguments().getStringArrayList("list2");
//        ArrayList<String> bronze_list= getArguments().getStringArrayList("list3");
        final ArrayList<String> gold_list= new ArrayList<>();
        final ArrayList<String> silver_list= new ArrayList<>();
        final ArrayList<String> bronze_list= new ArrayList<>();
        final DatabaseReference ref= database.getReference("Packages");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (getArguments().getString("flag")=="CarWash") {


                    for (DataSnapshot servSnapshot : dataSnapshot.child("CarWash").child("Gold").child("Services").getChildren()) {
                        String txt = servSnapshot.getValue(String.class);
                        gold_list.add(txt);
                    }
                    //int siz=gold_list.size();
                    String to_disp=prepare_string(gold_list);
//                    for (int i=0; i<siz-1; i++) { to_disp= to_disp+ gold_list.get(i)+" <br/>"; }
//                    if (siz>0){to_disp+=gold_list.get(siz-1);}


                    for (DataSnapshot servSnapshot : dataSnapshot.child("CarWash").child("Silver").child("Services").getChildren()) {
                        String txt = servSnapshot.getValue(String.class);
                        silver_list.add(txt);
                    }
                    //int siz2= silver_list.size();
                    String to_disp2=prepare_string(silver_list);
//                    for (int i=0; i<siz2-1; i++) { to_disp2= to_disp2+ silver_list.get(i)+" <br/>"; }
//                    if (siz2>0){to_disp2+=silver_list.get(siz2-1);}


                    for (DataSnapshot servSnapshot : dataSnapshot.child("CarWash").child("Bronze").child("Services").getChildren()) {
                        String txt = servSnapshot.getValue(String.class);
                        bronze_list.add(txt);
                    }
                    //int siz3= bronze_list.size();
                    String to_disp3=prepare_string(bronze_list);
//                    for (int i=0; i<siz3-1; i++) { to_disp3= to_disp3+ bronze_list.get(i)+" <br/>"; }
//                    if (siz3>0){to_disp3+=bronze_list.get(siz3-1);}


                    p_info.setText(Html.fromHtml(to_disp));
                    p_info2.setText(Html.fromHtml(to_disp2));
                    p_info3.setText(Html.fromHtml(to_disp3));



                }
                else {
                    for (DataSnapshot servSnapshot : dataSnapshot.child("OilChange").child("Gold").child("Services").getChildren()) {
                        String txt = servSnapshot.getValue(String.class);
                        gold_list.add(txt);
                    }

                    String to_disp= prepare_string(gold_list);
                    for (DataSnapshot servSnapshot : dataSnapshot.child("OilChange").child("Silver").child("Services").getChildren()) {
                        String txt = servSnapshot.getValue(String.class);
                        silver_list.add(txt);
                    }
                    String to_disp2= prepare_string(silver_list);
                    for (DataSnapshot servSnapshot : dataSnapshot.child("OilChange").child("Bronze").child("Services").getChildren()) {
                        String txt = servSnapshot.getValue(String.class);
                        bronze_list.add(txt);
                    }
                    String to_disp3= prepare_string(bronze_list);

                    p_info.setText(Html.fromHtml(to_disp));
                    p_info2.setText(Html.fromHtml(to_disp2));
                    p_info3.setText(Html.fromHtml(to_disp3));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),"error fetching from DB", Toast.LENGTH_LONG).show();

            }
        });



//        int siz=gold_list.size();
//        int siz2=silver_list.size();
//        int siz3= bronze_list.size();
//
//
//        String to_disp="";
//        for (int i=0; i<siz-1; i++) { to_disp= to_disp+ gold_list.get(i)+" <br/>"; }
//        if (siz>0){to_disp+=gold_list.get(siz-1);}
//
//        String to_disp2="";
//        for (int i=0; i<siz2-1; i++) { to_disp2= to_disp2+ silver_list.get(i)+" <br/>"; }
//        if (siz2>0){to_disp2+=silver_list.get(siz2-1);}
//
//        String to_disp3="";
//        for (int i=0; i<siz3-1; i++) { to_disp3= to_disp3+ bronze_list.get(i)+" <br/>"; }
//        if (siz3>0){to_disp3+=bronze_list.get(siz3-1);}
//
//
//
//        p_info.setText(Html.fromHtml(to_disp));
//        p_info2.setText(Html.fromHtml(to_disp2));
//        p_info3.setText(Html.fromHtml(to_disp3));


        return rootView;
        //return inflater.inflate(R.layout.fragment_serv_frag1, container, false);
    }


    private String prepare_string(ArrayList<String> list){
        int siz=list.size();
        String to_disp="";
        for (int i=0; i<siz-1; i++) { to_disp= to_disp+ list.get(i)+" <br/>"; }
        if (siz>0){to_disp+=list.get(siz-1);}

        return to_disp;
    }
    @Override
    public  void onViewCreated(View view , Bundle savedInstanceState) {

//        super.onViewCreated(view, savedInstanceState);
//        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(myDataset);
//        recyclerView.setAdapter(mAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
