package com.example.spyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listViewOfTrackers;
//    private TextView addNewTrackerTextView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("User");
//    private FirebaseAuth mAuth;
    private ArrayList<Tracker> arrayListOfTrackers = new ArrayList<>();
    private TrackerAdapter trackerAdapter;
//    MySharedPreferences msp;
    private Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_trackers);
        listViewOfTrackers = findViewById(R.id.ListOfTrackersActivity_ListView);
//        addNewTrackerTextView = findViewById(R.id.add_new_tracker_text_view);
//        addNewTrackerTextView.setOnClickListener(addNewTrackerFunc);
//        msp=new MySharedPreferences(this);
        trackerAdapter=new TrackerAdapter(this,arrayListOfTrackers);
        listViewOfTrackers.setAdapter(trackerAdapter);
//        FirebaseUser userUID = FirebaseAuth.getInstance().getCurrentUser();

        myRef.child("Trackers").addValueEventListener(new ValueEventListener() {//child(userUID.getUid()) if wont to add tracker to speciffic user
            @Override
            // Add Trackers to the list
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayListOfTrackers.clear();//Not necessary just for now in the end we just need to add the end of the array
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    tracker = ds.child("Location").getValue(Tracker.class);
                    arrayListOfTrackers.add(tracker);
                }
                listViewOfTrackers.setAdapter(trackerAdapter);//check if its necessary
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });



        listViewOfTrackers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Gson gson=new Gson();
                Tracker clickTracker= arrayListOfTrackers.get(i);
//                msp.putString(KEY_TRACKER,gson.toJson(clickTracker));
                //TODO: go to map activity
                goToNextActivity(ImagesListActivity.class, i);
            }
        });



    }

    private void goToNextActivity(Class activity, int i)
    {
        Intent intent = new Intent(this , activity);
        intent.putExtra("phone", arrayListOfTrackers.get(i).getPhoneNumber());

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }
}
