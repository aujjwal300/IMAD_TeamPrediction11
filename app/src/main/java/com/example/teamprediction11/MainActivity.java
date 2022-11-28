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
    public ArrayList<Players> getPlayers(){
        return players;
    }
    public void setPlayers(){
        Players player1 = new Players("Virat_Kohli", R.drawable.virat_kohli, "Right Hand Batsman");
        players.add(player1);

        Players player2 = new Players("Rohit_Sharma", R.drawable.rohit_sharma, "Right Hand Batsman");
        players.add(player2);

        Players player3 = new Players("Rishabh_Pant", R.drawable.rishabh_pant, "Wicket Keeper");
        players.add(player3);
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
}