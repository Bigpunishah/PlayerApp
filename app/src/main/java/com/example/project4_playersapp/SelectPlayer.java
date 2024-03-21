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
    private SharedPreferences selectedPlayersPreferences;


    //****************************************************************
    //Pickup here - need to assign the strings to the player selected in the main screen
    private boolean showCurrentPlayer = false;
    private SharedPreferences currentPlayersSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);

        //Clears the selected players back to none
        clearPlayerSelected();

        //Get all saved information from SharedPreferences player list
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
                selectPlayer(selectedItem, position);
                Toast.makeText(SelectPlayer.this, "Selected: " + selectedItem + " Position: " + position, Toast.LENGTH_SHORT).show();

            }
        });


        //Create the player selection SharedPreferences
        //This allows the user to select a player from player list & store them into the player 1 or 2 slot SP (SharedPrefs)
        selectedPlayersPreferences = getSharedPreferences("SelectedPlayerPrefs", Context.MODE_PRIVATE);

    }

    // Method to select a player
    public void selectPlayer(String playerName, int playerNumber) {
        selectedPlayersPreferences = getSharedPreferences("SelectedPlayerPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = selectedPlayersPreferences.edit();

        switch(playerNumber){
            case 0:
                String key = "Player" + playerNumber;
                editor.putString(key, playerName);
                editor.apply();
                return;

            case 1:
                editor.putString("Player" + playerNumber, playerName);
                editor.apply();
                return;

            default:
                Toast.makeText(this, "Player already selected!", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearPlayerSelected(){
        selectedPlayersPreferences = getSharedPreferences("SelectedPlayerPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = selectedPlayersPreferences.edit();
        editor.clear();
        editor.apply();
    }
}