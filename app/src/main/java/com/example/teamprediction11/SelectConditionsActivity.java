package com.example.teamprediction11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class SelectConditionsActivity extends AppCompatActivity {
    String[] opponentArr, venueArr, pitchArr, timeArr;
    AutoCompleteTextView opponentTeamSpinner, venueSpinner, pitchSpinner, timeSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_conditions);

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

    public void onNextButtonClick(View view) {
        Intent i = new Intent(this, Result.class);
        startActivity(i);
    }
}