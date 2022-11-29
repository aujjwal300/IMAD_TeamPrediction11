package com.example.teamprediction11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ListAdapter adapter;
    private ArrayList<Players> players = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(FirebaseAuth.getInstance().getCurrentUser() == null){
//            startLogin();
//        }

        mRecyclerView = findViewById(R.id.recyclerview);

        setPlayers();

        adapter = new ListAdapter(this, players);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(adapter);
    }

    public void onNextButtonClick(View view) {
        Intent i = new Intent(this, SelectConditionsActivity.class);
        startActivity(i);
    }

    public void startLogin(){
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void handleLogout(View view) {
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    startLogin();
            }
        });
    }

    public void setPlayers(){
        Players player1 = new Players("Virat_Kohli", R.drawable.virat_kohli, "Right Hand Batsman");
        players.add(player1);
        Players player2 = new Players("Rohit_Sharma", R.drawable.rohit_sharma, "Right Hand Batsman");
        players.add(player2);
        Players player3 = new Players("Rishabh_Pant", R.drawable.rishabh_pant, "Left hand Batsman");
        players.add(player3);
        Players player4 = new Players("Ravindra_Jadeja", R.drawable.jadeja, "Left Hand Batsman");
        players.add(player4);
        Players player6 = new Players("Suryakumar_Yadav", R.drawable.suryakumar, "Right Hand Batsman");
        players.add(player6);
        Players player7 = new Players("Shikhar_Dhawan", R.drawable.dhawan, "Left Hand Batsman");
        players.add(player7);
        Players player8 = new Players("Ishan_Kishan", R.drawable.ishan, "Left Hand Batsman");
        players.add(player8);
        Players player9 = new Players("Gautam_Gambhir", R.drawable.gautam_gambhir, "Left Hand Batsman");
        players.add(player9);
        Players player10 = new Players("Shreyas_Iyer", R.drawable.shreyas, "Right Hand Batsman");
        players.add(player10);
        Players player11 = new Players("Shubman_Gill", R.drawable.shubhman, "Right Hand Batsman");
        players.add(player11);
        Players player15 = new Players("Virender_Sehwag", R.drawable.sehwag, "Right Hand Batsman");
        players.add(player15);
        Players player16 = new Players("MS_Dhoni", R.drawable.ms_dhoni, "Right Hand Batsman");
        players.add(player16);
        Players player17 = new Players("Suresh_Raina", R.drawable.raina, "Left Hand Batsman");
        players.add(player17);
        Players player12 = new Players("Hardik_Pandya", R.drawable.hardik_pandya, "Right Hand Batsman");
        players.add(player12);
        Players player5 = new Players("Yuvraj_Singh", R.drawable.yuvraj, "Left Hand Batsman");
        players.add(player5);
        Players player13 = new Players("Dinesh_Karthik", R.drawable.dinesh_kartik, "Right Hand Batsman");
        players.add(player13);
        Players player14 = new Players("Sanju_Samson", R.drawable.sanju_samson, "Right Hand Batsman");
        players.add(player14);
    }
}