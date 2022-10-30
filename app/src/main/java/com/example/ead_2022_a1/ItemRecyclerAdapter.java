package com.example.ead_2022_a1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.MyViewHolder>{
    private Context context;
    private List<FuelStation> itemList;
    private String userName,vehicleId;

    public ItemRecyclerAdapter( Context context, List<FuelStation> itemList , String userName , String vehicleId) {
        this.itemList = itemList;
        this.context = context;
        this.userName = userName;
        this.vehicleId = vehicleId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String name = itemList.get(position).getName();
        holder.fsName.setText(name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserJoinQueue.class);
                intent.putExtra("ownerName", itemList.get(position).getUserName());
                intent.putExtra("userName", userName);
                intent.putExtra("vehicleId", vehicleId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fsName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fsName = itemView.findViewById(R.id.fsName);
        }
    }
}
