package com.example.project4_playersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Map;

public class SelectPlayer extends AppCompatActivity {

    private ListView playersListView;
    private ArrayAdapter<String> arrayAdapter;

//    private String[] currentPlayers = {"Jay", "Bob"}; for reference only
    private SharedPreferences sharedPreferences;


    //****************************************************************
    //Pickup here - need to assing the strings to the player selected in the main screen
    private boolean showCurrentPlayer = false;
    private SharedPreferences currentPlayersSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);

        //Get all saved information from SharedPreferences
        sharedPreferences = getSharedPreferences("PlayerPrefs", Context.MODE_PRIVATE);
        Map<String, ?> playersInMap = sharedPreferences.getAll();
        int playerCount = playersInMap.size();

        //String array to hold players names
        //assign players within the array
        String[] currentPlayers = playersInMap.values().toArray(new String[0]); //0 is preferred over `int size`

        // Reference to ListView
        playersListView = (ListView) findViewById(R.id.playersListView);

        // Create ArrayAdapter to populate the ListView
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currentPlayers);

        // Set ArrayAdapter to ListView
        playersListView.setAdapter(arrayAdapter);

        // Set item click listener
        playersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click
                String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(SelectPlayer.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();

            }
        });
    }

    // Method to add a new player
    public void addPlayer(String playerName) {
        //
    }
}