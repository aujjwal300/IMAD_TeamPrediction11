package com.example.teamprediction11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ListAdapter adapter;
    private ArrayList<Players> players = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String opponent, venue, pitch, time;
    private Map<String, String> opponentMap = new HashMap<String,String>();
    private Map<String, String> venueMap = new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setData();

        Intent intent = getIntent();
        opponent = opponentMap.get(intent.getStringExtra("opponent"));
        venue = intent.getStringExtra("venue");
        pitch = intent.getStringExtra("pitch");
        time = intent.getStringExtra("time");

//        getData();

        mRecyclerView = findViewById(R.id.recyclerview2);
        setLayout();
    }

    void getData(){
        db.collection(opponent).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        Log.d("Ujjwal1", String.valueOf(list));
                        for(DocumentSnapshot docSnap : list){
                            Log.d("Ujjwal2", String.valueOf(docSnap));
                            try {
                                Stats st = docSnap.toObject(Stats.class);
                                Log.d("Ujjwal3", String.valueOf(st.getName()));
                            }
                            catch (Exception e){
                                Log.e("Ujjwal4", e.getMessage());
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ujjwal", e.getMessage());
                    }
                });
    }

    void setData(){
        opponentMap.put("Australia", "vsAus");
        opponentMap.put("South Africa", "vsSaf");
        opponentMap.put("England", "vsEng");
        opponentMap.put("New Zealand", "vsNZ");
        opponentMap.put("West Indies", "vsWI");
    }

    void setLayout(){
        Toast.makeText(this, opponent, Toast.LENGTH_SHORT).show();
        adapter = new ListAdapter(getApplicationContext(), players);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(adapter);
    }
}