package com.example.teamprediction11;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private Context context;
    private List<String> names;
    private List<Integer> images;
    private List<String> descr;

    public ListAdapter(Context context, List<String> names, List<Integer> images, List<String> descr){
        this.context = context;
        this.names = names;
        this.images = images;
        this.descr = descr;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.grid_player_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mTextView1.setText(names.get(position));
        holder.mImageView.setImageResource(images.get(position));
        holder.mTextView2.setText(descr.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
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
