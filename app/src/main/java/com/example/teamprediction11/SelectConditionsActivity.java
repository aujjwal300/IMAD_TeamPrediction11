package com.example.teamprediction11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SelectConditionsActivity extends AppCompatActivity {
    String[] opponentArr, venueArr, pitchArr, timeArr;
    AutoCompleteTextView opponentTeamSpinner, venueSpinner, pitchSpinner, timeSpinner;
    String opponentVal = "", venueVal = "", pitchVal = "", timeVal = "";
    private String opponent, venue, pitch, time;
    private Map<String, ArrayList<Float>> playerStats = new HashMap<String,ArrayList<Float>>();
    private Map<String, Float> performance = new HashMap<String, Float>();
    public ArrayList<String> players11 = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_conditions);

        setupLayout();
        setupListener();
    }

    public void onNextButtonClick(View view) {
        if(opponentVal.equalsIgnoreCase("") || venueVal.equalsIgnoreCase("") || pitchVal.equalsIgnoreCase("") || timeVal.equalsIgnoreCase("")){
            Toast.makeText(this, "Select all the conditions", Toast.LENGTH_SHORT).show();
        }
        else {
            opponent = "vs" + hashing(opponentVal);
            venue = "in" + hashing(venueVal);
            pitch = pitchVal;
            timeVal = time;

            getData();
            new CountDownTimer(3000, 10000){
                @Override
                public void onTick(long l) {}

                @Override
                public void onFinish() {
                    computeData();
                }
            }.start();
            new CountDownTimer(6000, 10000){
                @Override
                public void onTick(long l) {}

                @Override
                public void onFinish() {
                    finalAnswer();
                }
            }.start();

            Intent i = new Intent(this, Result.class);
            new CountDownTimer(10000, 10000){

                @Override
                public void onTick(long l) {}

                @Override
                public void onFinish() {
                    Log.d("OpGet", String.valueOf(players11));
                    i.putStringArrayListExtra("players11",players11);
                    startActivity(i);
                }
            }.start();

        }
    }

    String hashing(String value){
        switch (value){
            case "Australia":
                return "Aus";
            case "South Africa":
                return "Saf";
            case "India":
                return "Ind";
            case "England":
                return "Eng";
            case "New Zealand":
                return "NZ";
            case "West Indies":
                return "WI";
        }

        return "";
    }

    void finalAnswer(){
        Map<String, Float> sortedMap = sortByValue(performance);

        int count = 0;
        for(Map.Entry<String,Float> entry : sortedMap.entrySet()){
            count++;
            players11.add(entry.getKey());
            Log.d("OpGet", String.valueOf(entry.getKey()));
            if(count == 11)
                break;
        }
    }

    void computeData(){
        for (Map.Entry<String, ArrayList<Float>> entry : playerStats.entrySet()){
            ArrayList<Float> stats = entry.getValue();
            Log.d("OpGet4", entry.getKey() + " " + String.valueOf(stats));
            callAPIforPerformance(entry.getKey(), String.valueOf(stats.get(0)), String.valueOf(Math.round(stats.get(1))), String.valueOf(stats.get(2)), String.valueOf(Math.round(stats.get(3))), String.valueOf(Math.round(stats.get(4))));
        }
    }

    private void callAPIforPerformance(String name, String avg, String bf, String str, String num4s, String num6s) {
        RequestQueue queue = Volley.newRequestQueue(SelectConditionsActivity.this);
        String url = String.valueOf("https://imad-api.herokuapp.com/predict_runs?Player="+name+"&Avg="+avg+"&Bf="+bf+"&Str="+str+"&Fours="+num4s+"&Six="+num6s);
        Log.d("OpGet3", String.valueOf(url));
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Float perfVal;
                try {
                    perfVal = Float.valueOf(response);
                    performance.put(name, perfVal);
                } catch (Exception e) {
                    Log.d("OpGet1", e.getMessage());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OpGet2", error.getMessage());
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    void getData(){
        db.collection(opponent).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot docSnap : list){
                            try {
                                Stats st = docSnap.toObject(Stats.class);

                                ArrayList<Float> stats = new ArrayList<>();
                                stats.add(st.getAvg());
                                stats.add(st.getBf());
                                stats.add(st.getStr());
                                stats.add(st.getNum4s());
                                stats.add(st.getNum6s());

                                playerStats.put(st.getPlayer_name(), stats);
                                Log.d("OpGet", String.valueOf(st.getPlayer_name()));
                            }
                            catch (Exception e){
                                Log.e("OpGetFirebase", e.getMessage());
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("OpGetFirebase", e.getMessage());
                    }
                });

        db.collection(venue).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot docSnap : list){
                            try {
                                Stats st = docSnap.toObject(Stats.class);
                                Log.d("VenGet", String.valueOf(st.getPlayer_name()));
                                ArrayList<Float> stats = playerStats.get(String.valueOf(st.getPlayer_name()));
                                stats.set(0, (stats.get(0) + st.getAvg()) / 2);
                                stats.set(1, (stats.get(1) + st.getBf()));
                                stats.set(2, (stats.get(2) + st.getStr()) / 2);
                                stats.set(3, (stats.get(3) + st.getNum4s()));
                                stats.set(4, (stats.get(4) + st.getNum6s()));

                                playerStats.put(st.getPlayer_name(), stats);
                            }
                            catch (Exception e){
                                Log.e("VenGetFirebase", e.getMessage());
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("VenGetFirebase", e.getMessage());
                    }
                });

    }

    private static Map<String, Float> sortByValue(Map<String, Float> unsortMap) {
        List<Map.Entry<String, Float>> list =
                new LinkedList<Map.Entry<String, Float>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {
            public int compare(Map.Entry<String, Float> o1,
                               Map.Entry<String, Float> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<String, Float> sortedMap = new LinkedHashMap<String, Float>();
        for (Map.Entry<String, Float> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public void setupLayout(){
        opponentTeamSpinner = (AutoCompleteTextView) findViewById(R.id.opponentTeam);
        venueSpinner = (AutoCompleteTextView) findViewById(R.id.venue);
        pitchSpinner = (AutoCompleteTextView) findViewById(R.id.pitchCondition);
        timeSpinner = (AutoCompleteTextView) findViewById(R.id.time);

        opponentArr = getResources().getStringArray(R.array.opponent_team);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.dropdown_item, opponentArr);
        opponentTeamSpinner.setAdapter(adapter1);

        venueArr = getResources().getStringArray(R.array.venue);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.dropdown_item, venueArr);
        venueSpinner.setAdapter(adapter2);

        pitchArr = getResources().getStringArray(R.array.pitch_condition);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.dropdown_item, pitchArr);
        pitchSpinner.setAdapter(adapter3);

        timeArr = getResources().getStringArray(R.array.time);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.dropdown_item, timeArr);
        timeSpinner.setAdapter(adapter4);
    }

    public void setupListener(){
        opponentTeamSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                opponentVal = opponentArr[i];
            }
        });

        venueSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                venueVal = venueArr[i];
            }
        });

        pitchSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pitchVal = pitchArr[i];
            }
        });

        timeSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                timeVal = timeArr[i];
            }
        });
    }
}