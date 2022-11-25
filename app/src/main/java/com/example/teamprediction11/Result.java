package com.example.teamprediction11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Result extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> names;
    private List<Integer> mImages;
    private List<String> descp;
    private ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mRecyclerView = findViewById(R.id.recyclerview2);
        mRecyclerView.setNestedScrollingEnabled(false);

        names = new ArrayList<>();
        mImages = new ArrayList<>();
        descp = new ArrayList<>();

        adapter = new ListAdapter(this, names, mImages, descp);

        mImages.add(R.drawable.virat_kohli);
        names.add("Virat Kohli");
        descp.add("Righ Hand Batsman");

        mImages.add(R.drawable.rohit_sharma);
        names.add("Rohit Sharma");
        descp.add("Righ Hand Batsman");

        mImages.add(R.drawable.rishabh_pant);
        names.add("Rishabh Pant");
        descp.add("Wicket Keeper");

        mImages.add(R.drawable.virat_kohli);
        names.add("Virat Kohli");
        descp.add("Spin Bowler");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(adapter);
    }
}