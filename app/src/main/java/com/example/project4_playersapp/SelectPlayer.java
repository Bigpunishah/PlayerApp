package com.example.project4_playersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

    //    private String[] currentPlayers = {"Jay", "Bob"}; // for reference only
    private SharedPreferences sharedPreferences;
    private SharedPreferences selectedPlayersPreferences;


    //****************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);

        //Clears the selected players back to none
        //clearPlayerSelected();

        //Create the player selection SharedPreferences
        //This allows the user to select a player from player list & store them into the player 1 or 2 slot SP (SharedPrefs)
        selectedPlayersPreferences = getSharedPreferences("SelectedPlayerPrefs", Context.MODE_PRIVATE);

        //Get all saved information from SharedPreferences player list
        sharedPreferences = getSharedPreferences("PlayerPrefs", Context.MODE_PRIVATE);
        Map<String, ?> playersInMap = sharedPreferences.getAll();
        int playerCount = playersInMap.size();

        //String array to hold players names
        //assign players within the array
        String[] currentPlayers = playersInMap.values().toArray(new String[0]); //0 is preferred over `int size`

        // Reference to ListView
        playersListView = findViewById(R.id.playersListView);

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
                selectPlayer(selectedItem);
                Toast.makeText(SelectPlayer.this, "Selected: " + selectedItem + " Position: " + position, Toast.LENGTH_SHORT).show();

            }
        });




    }

    /*
    Currently the player selected is coming along but...
    the selection is not accurate for each player. Maybe try & fix that.
     */

    // Method to select a player
    public void selectPlayer(String playerName) {
        SharedPreferences.Editor editor = selectedPlayersPreferences.edit();
        //get the size so we dont use the listview position anymore
        int sizeForPlayersSelected = selectedPlayersPreferences.getAll().size();
        //Saving the selected player with key & name
        String playerSelectedFromPrefs = selectedPlayersPreferences.getString("Player" + sizeForPlayersSelected + 1, playerName);


        if(playerName.equals(playerSelectedFromPrefs)){
            Toast.makeText(this, "Player selected", Toast.LENGTH_SHORT).show();

            editor.putString("Player" + sizeForPlayersSelected + 1 , playerName); //position is key & playername is value.
            editor.apply();
            startMainMenuActivity();
        } else {
            Toast.makeText(this, "Player already selected", Toast.LENGTH_SHORT).show();
        }




//        switch(playerNumber){
//            case 0:
//                String key = "Player" + playerNumber;
//                editor.putString(key, playerName);
//                editor.apply();
//                // Check if both players are selected, if yes, start the main menu activity
//                if (selectedPlayersPreferences.contains("Player0") && selectedPlayersPreferences.contains("Player1")) {
//                    startMainMenuActivity();
//                }
//                return;
//
//            case 1:
//                editor.putString("Player" + playerNumber, playerName);
//                editor.apply();
//                // Check if both players are selected, if yes, start the main menu activity
//                if (selectedPlayersPreferences.contains("Player0") && selectedPlayersPreferences.contains("Player1")) {
//                    startMainMenuActivity();
//                }
//                return;
//
//            default:
//                Toast.makeText(this, "Player already selected!", Toast.LENGTH_SHORT).show();
//        }
    }

    // Method to start the main menu activity
    private void startMainMenuActivity() {
        // Pass selected players to main menu activity
        Intent intent = new Intent(SelectPlayer.this, MainActivity.class);
        startActivity(intent);
        // Finish current activity to prevent returning to player selection screen
        finish();
    }

//    public void clearPlayerSelected(){
//        selectedPlayersPreferences = getSharedPreferences("SelectedPlayerPrefs", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = selectedPlayersPreferences.edit();
//        editor.clear();
//        editor.apply();
//    }
}
