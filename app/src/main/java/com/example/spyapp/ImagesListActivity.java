package com.example.spyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ImagesListActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ArrayList<String> listOfImagesInStringFormat=new ArrayList<>();
    private ImageAdapter listOfImagesInStringFormatAdapter;
    private ListView listViewOfImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_list);
        Intent i = getIntent();
        String phone = i.getStringExtra("phone");
        //String phoneNumber = tracker.getPhoneNumber();
        Log.i("phoneNumber",phone);
        listViewOfImages=findViewById(R.id.ListOfImagesListActivity_ListView);
        DatabaseReference myRef = database.getReference("User");
        listOfImagesInStringFormatAdapter=new ImageAdapter(this,listOfImagesInStringFormat);
        listViewOfImages.setAdapter(listOfImagesInStringFormatAdapter);
        myRef.child("Trackers").child(phone).child("photo").addValueEventListener(new ValueEventListener() {
            @Override
            // Add Trackers to the list
            public void onDataChange(DataSnapshot dataSnapshot) {
                listOfImagesInStringFormat.clear();//Not necessary just for now in the end we just need to add the end of the array
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.i("phoneNumber","ghfgjghj");

                    String photo = ds.getValue(String.class);
                    listOfImagesInStringFormat.add(photo);

                }
                listViewOfImages.setAdapter(listOfImagesInStringFormatAdapter);//check if its necessary
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

    }
}