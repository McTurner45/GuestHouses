package com.example.guesthouses;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guesthouses.Adapters.GuestHouseAdapter;
import com.example.guesthouses.Adapters.SearchAdapter;
import com.example.guesthouses.Model.GuestHouse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    RecyclerView vertical, horizontal;

    GuestHouseAdapter houseAdapter;
    SearchAdapter searchAdapter;

    ArrayList<GuestHouse> houses, list;

    EditText searchHouse;

    androidx.appcompat.widget.SearchView searchView;

    Button button;

    GuestHouse house;

    final static int image_picked = 1;

    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vertical = findViewById(R.id.recyclerview_vertical);

        button = findViewById(R.id.add);
        horizontal = findViewById(R.id.recyclerview_horizontal);
        searchView = findViewById(R.id.search_bar);

        houses = new ArrayList<>();

        vertical.setHasFixedSize(false);
        vertical.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();

        reference = firebaseDatabase.getReference("guesthouses").child("houses");

        mStorageRef = FirebaseStorage.getInstance().getReference().child("HouseImages");

        GuestHouse house= new GuestHouse("9873297362378","Bahumi", "100", "Gabs"
                , "5", "Default", "77000826", "default");


        getData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImages();
            }
        });

    }

    private void saveData() {

        firebaseDatabase = FirebaseDatabase.getInstance();

        reference = firebaseDatabase.getReference("guesthouses").child("houses");

        house= new GuestHouse("9873297362378","Bahumi", "100", "Gabs"
                , "5", "Default", "77000826", "default");

        reference.push().setValue(house);
    }

    private void saveImages() {
        firebaseDatabase = FirebaseDatabase.getInstance();

        reference = firebaseDatabase.getReference("guesthouses").child("houses");

        Intent getImageIntent = new Intent();

        getImageIntent.setAction(Intent.ACTION_GET_CONTENT);
        getImageIntent.setType("images/*");
        startActivityForResult(getImageIntent, image_picked);

    }

    public void getData() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                houses.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.exists()) {
                        GuestHouse house = dataSnapshot.getValue(GuestHouse.class);
                        houses.add(house);
                    }
                }

                houseAdapter = new GuestHouseAdapter(houses);

                vertical.setAdapter(houseAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return false;
                }
            });
        }
    }

    void search(String s) {
        ArrayList<GuestHouse> houses1 = new ArrayList<>();
        for (GuestHouse object : houses) {
            if (object.getName().toLowerCase().contains(s)) {
                houses1.add(object);
            }

        }
        searchAdapter = new SearchAdapter(houses1);

        vertical.setAdapter(searchAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == image_picked && resultCode == RESULT_OK && data != null) {

            Uri imageUrl = data.getData();

            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (requestCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                StorageReference filePath= mStorageRef.child(house.getHouseId() + ".jpg");

                filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){
                            final String downloadUrl = task.getResult().getUploadSessionUri().toString();
                        }
                    }
                });
            }
        }
    }
}