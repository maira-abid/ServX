package pl.servx.servx.Model;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;

public class car_list {
    DatabaseReference db;
    Boolean saved=null;

   public static ArrayList<String> cars= new ArrayList<>();

    public car_list(DatabaseReference db) {
        this.db = db;
    }

    public ArrayList<String> retrieve()
    {
        final ArrayList<String> spacecrafts=new ArrayList<>();

        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot,spacecrafts);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot,spacecrafts);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return spacecrafts;
    }

    public ArrayList<String> fetchData(DataSnapshot snapshot,ArrayList<String> spacecrafts)
    {
        ArrayList<String> cars =new ArrayList<>();
        spacecrafts.clear();
        for (DataSnapshot ds:snapshot.getChildren())
        {
            String name= String.valueOf(ds.child("03361424139").child("vehicle"));
            spacecrafts.add(name);
            cars.add(name);
        }

        return cars;

    }
}

