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
        Players player3 = new Players("Rishabh_Pant", R.drawable.rishabh_pant, "Left hand Batsman");
        addPlayer(player3);
        Players player4 = new Players("Ravindra_Jadeja", R.drawable.jadeja, "Left Hand Batsman");
        addPlayer(player4);
        Players player6 = new Players("Suryakumar_Yadav", R.drawable.suryakumar, "Right Hand Batsman");
        addPlayer(player6);
        Players player7 = new Players("Shikhar_Dhawan", R.drawable.dhawan, "Left Hand Batsman");
        addPlayer(player7);
        Players player8 = new Players("Ishan_Kishan", R.drawable.ishan, "Left Hand Batsman");
        addPlayer(player8);
        Players player9 = new Players("Gautam_Gambhir", R.drawable.gautam_gambhir, "Left Hand Batsman");
        addPlayer(player9);
        Players player10 = new Players("Shreyas_Iyer", R.drawable.shreyas, "Right Hand Batsman");
        addPlayer(player10);
        Players player11 = new Players("Shubman_Gill", R.drawable.shubhman, "Right Hand Batsman");
        addPlayer(player11);
        Players player15 = new Players("Virender_Sehwag", R.drawable.sehwag, "Right Hand Batsman");
        addPlayer(player15);
        Players player16 = new Players("MS_Dhoni", R.drawable.ms_dhoni, "Right Hand Batsman");
        addPlayer(player16);
        Players player17 = new Players("Suresh_Raina", R.drawable.raina, "Left Hand Batsman");
        addPlayer(player17);
        Players player12 = new Players("Hardik_Pandya", R.drawable.hardik_pandya, "Right Hand Batsman");
        addPlayer(player12);
        Players player5 = new Players("Yuvraj_Singh", R.drawable.yuvraj, "Left Hand Batsman");
        addPlayer(player5);
        Players player13 = new Players("Dinesh_Karthik", R.drawable.dinesh_kartik, "Right Hand Batsman");
        addPlayer(player13);
        Players player14 = new Players("Sanju_Samson", R.drawable.sanju_samson, "Right Hand Batsman");
        addPlayer(player14);
    }

    void setLayout(){
        adapter = new ListAdapter(getApplicationContext(), finalPlayers);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(adapter);
    }
}