package com.example.teamprediction11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;


public class Result extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ListAdapter adapter;
    private ArrayList<Players> finalPlayers = new ArrayList<>();
    private ArrayList<String> players11 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        players11 = intent.getStringArrayListExtra("players11");

        setPlayers();
        
        mRecyclerView = findViewById(R.id.recyclerview2);
        setLayout();
    }

    void addPlayer(Players p){
        if(players11.contains(p.getName())){
            finalPlayers.add(p);
        }
    }

    public void setPlayers(){
        Players player1 = new Players("Virat_Kohli", R.drawable.virat_kohli, "Right Hand Batsman");
        addPlayer(player1);

        Players player2 = new Players("Rohit_Sharma", R.drawable.rohit_sharma, "Right Hand Batsman");
        addPlayer(player2);

        Players player3 = new Players("Rishabh_Pant", R.drawable.rishabh_pant, "Wicket Keeper");
        addPlayer(player3);
    }

    void setLayout(){
        adapter = new ListAdapter(getApplicationContext(), finalPlayers);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(adapter);
    }
}