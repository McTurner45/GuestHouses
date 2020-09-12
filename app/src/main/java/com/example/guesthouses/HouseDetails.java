package com.example.guesthouses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guesthouses.Adapters.ImagesAdapter;
import com.example.guesthouses.Adapters.TabAdapter;
import com.example.guesthouses.Fragments.Details;
import com.example.guesthouses.Fragments.Reviews;
import com.example.guesthouses.Model.HousePreviewImages;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class HouseDetails extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    ImageView imageView;
    TextView gH_name;
    TextView gH_price;
    TextView gH_location;
    TextView gH_rating;
    TextView gH_phone;

    RecyclerView houseImages;

    ArrayList<HousePreviewImages> list;

    ImagesAdapter imagesAdapter;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);

        gH_name = findViewById(R.id.guest_house_name);
        gH_price = findViewById(R.id.guest_house_price);
        gH_location = findViewById(R.id.guest_house_location);
        gH_rating = findViewById(R.id.guest_house_rating);
        gH_phone = findViewById(R.id.house_phone);

        houseImages = findViewById(R.id.guest_house_image);

        firebaseDatabase = FirebaseDatabase.getInstance();

        houseImages.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        houseImages.setLayoutManager(linearLayoutManager);
        houseImages.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        list = new ArrayList<>();

        imagesAdapter = new ImagesAdapter(this, list);

        houseImages.setAdapter(imagesAdapter);

        Intent intent = getIntent();

        gH_name.setText(intent.getStringExtra("gH_name"));
        gH_price.setText(intent.getStringExtra("gH_price"));
        gH_location.setText(intent.getStringExtra("gH_location"));
        gH_rating.setText(intent.getStringExtra("gH_rating"));
        gH_phone.setText(intent.getStringExtra("gH_phone"));

        getImages(intent.getStringExtra("gH_images"));

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(),
                this, tabLayout.getTabCount());

        viewPager.setAdapter(tabAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void getImages(final String houseName) {
        reference=firebaseDatabase.getReference("HouseImages");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    HousePreviewImages housePreviewImages= dataSnapshot.getValue(HousePreviewImages.class);

                    if (housePreviewImages.getGuestHouseName().equals(houseName)){
                        list.add(housePreviewImages);
                    }
                }

                Collections.reverse(list);
                imagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}