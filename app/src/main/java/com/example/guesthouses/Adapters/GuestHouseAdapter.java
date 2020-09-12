package com.example.guesthouses.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guesthouses.HouseDetails;
import com.example.guesthouses.Model.GuestHouse;
import com.example.guesthouses.R;

import java.util.List;

public class GuestHouseAdapter extends RecyclerView.Adapter<GuestHouseAdapter.ViewHolder>{

    Context mContext;
    List<GuestHouse> mGuestHouse;

    public GuestHouseAdapter(List<GuestHouse> mGuestHouse) {
        this.mGuestHouse = mGuestHouse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mContext = parent.getContext();

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.vertical_guest_house_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final GuestHouse guestHouse= mGuestHouse.get(position);

        holder.gH_phone.setText(guestHouse.getPhone());
        holder.gH_price.setText(guestHouse.getPrice());
        holder.gH_rating.setText(guestHouse.getRating());
        holder.gH_location.setText(guestHouse.getLocation());
        holder.gH_name.setText(guestHouse.getName());

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HouseDetails.class);
                intent.putExtra("gh_phone", guestHouse.getPhone());
                intent.putExtra("gH_price", guestHouse.getPrice());
                intent.putExtra("gH_rating", guestHouse.getRating());
                intent.putExtra("gH_location", guestHouse.getLocation());
                intent.putExtra("gH_name", guestHouse.getName());
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {

        return mGuestHouse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView gH_name;
        TextView gH_price;
        TextView gH_location;
        TextView gH_rating;
        TextView gH_phone;

        CardView parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.guest_house_image);
            gH_name= itemView.findViewById(R.id.guest_house_name);
            gH_location= itemView.findViewById(R.id.guest_house_location);
            gH_price= itemView.findViewById(R.id.guest_house_price);
            gH_rating= itemView.findViewById(R.id.guest_house_rating);
            gH_phone= itemView.findViewById(R.id.guest_house_phone);
            parent_layout=itemView.findViewById(R.id.parent_layout);
        }
    }
}
