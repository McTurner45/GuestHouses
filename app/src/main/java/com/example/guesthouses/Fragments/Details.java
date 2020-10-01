package com.example.guesthouses.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guesthouses.HouseDetails;
import com.example.guesthouses.Model.GuestHouse;
import com.example.guesthouses.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Details extends Fragment {

    String houseId, description;

    TextView descriptionTxtView;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HouseDetails houseDetails = (HouseDetails) getActivity();

        assert houseDetails != null;
        Bundle results = houseDetails.getMyData();

        houseId = results.getString("houseId");

        firebaseDatabase = FirebaseDatabase.getInstance();

        reference = firebaseDatabase.getReference("guesthouses");

        reference.child("houses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    GuestHouse house = dataSnapshot.getValue(GuestHouse.class);

                    if (house.getHouseId().equals(houseId)) {

                        description = house.getDescription();
                        Log.d("message", description);

                        descriptionTxtView.setText("");

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        descriptionTxtView = (TextView) container.findViewById(R.id.description);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }
}