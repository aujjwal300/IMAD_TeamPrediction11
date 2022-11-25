package com.example.teamprediction11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> names;
    private List<Integer> mImages;
    private List<String> descp;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startLogin();
        }

        mRecyclerView = findViewById(R.id.recyclerview);

        names = new ArrayList<>();
        mImages = new ArrayList<>();
        descp = new ArrayList<>();

        adapter = new ListAdapter(this, names, mImages, descp);

        mImages.add(R.drawable.virat_kohli);
        names.add("Virat Kohli");
        descp.add("Right Hand Batsman");

        mImages.add(R.drawable.rohit_sharma);
        names.add("Rohit Sharma");
        descp.add("Right Hand Batsman");

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