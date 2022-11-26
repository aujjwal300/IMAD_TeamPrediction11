package com.example.teamprediction11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Players> mPlayers = new ArrayList<>();

    public ListAdapter(Context context, ArrayList<Players> players){
        this.context = context;
        mPlayers = players;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.grid_player_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mTextView1.setText(mPlayers.get(position).getName());
        holder.mImageView.setImageResource(mPlayers.get(position).getImage());
        holder.mTextView2.setText(mPlayers.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
         ImageView mImageView;
         TextView mTextView1;
         TextView mTextView2;

         public MyViewHolder(View itemView) {
             super(itemView);

             mImageView = itemView.findViewById(R.id.imageview);
             mTextView1 = itemView.findViewById(R.id.nametextview);
             mTextView2 = itemView.findViewById(R.id.desctextview);
         }
    }
}
