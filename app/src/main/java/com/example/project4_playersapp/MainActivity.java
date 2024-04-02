package com.example.project4_playersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button startGameButton;
    private Button viewScoreBoardButton;
    private Button selectPlayerOneButton;
    private Button selectPlayerTwoButton;
    private Button addPlayerButton;
    private TextView currentPlayer1TextView;
    private TextView currentPlayer2TextView;

    private SharedPreferences selectedPlayersPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //reference the id's
        startGameButton = (Button) findViewById(R.id.startGameButton);
        viewScoreBoardButton = (Button) findViewById(R.id.viewScoreboardButton);
        selectPlayerOneButton = (Button) findViewById(R.id.selectPlayerOneButton);
        selectPlayerTwoButton = (Button) findViewById(R.id.selectPlayerTwoButton);
        addPlayerButton = (Button) findViewById(R.id.addPlayerButton);
        currentPlayer1TextView = (TextView) findViewById(R.id.currentSelectedPlayer1TextView);
        currentPlayer2TextView = (TextView) findViewById(R.id.currentSelectedPlayer2TextView);


        //update string names for players
        selectedPlayersPreferences = getSharedPreferences("SelectedPlayerPrefs", Context.MODE_PRIVATE);
        //Pick up here on updating players

        //Clear the current selected players.
//        clearSelectedPlayers();

        //Set OnClickListener for the buttons to switch screens

            //The game can start once inside here
            // Set OnClickListener for the buttons
            startGameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String player1 = currentPlayer1TextView.getText().toString();
                    String player2 = currentPlayer2TextView.getText().toString();
                    if (!player1.isEmpty() && !player2.isEmpty()) {
                        // Start the game emulator activity
                        Intent intent = new Intent(MainActivity.this, GameEmulator.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Player 1 and Player 2 must be selected to play", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            //Start the scoreboard activity
            viewScoreBoardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Scoreboard.class);
                    startActivity(intent);
                }
            });

            //Start the select player activity
            selectPlayerOneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, SelectPlayer.class);
                    startActivity(intent);
                }
            });

            //Same as above!
            selectPlayerTwoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, SelectPlayer.class);
                    startActivity(intent);
                }
            });

            //Start the add player activity
            addPlayerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, AddPlayer.class);
                    startActivity(intent);
                }
            });

            // Update selected players when returning from SelectPlayer activity
            updateSelectedPlayers();
        }//end onCreate


        // Method to update selected players when returning from SelectPlayer activity
        private void updateSelectedPlayers () {
//            SharedPreferences.Editor editor = selectedPlayersPreferences.edit();

//            Map<String, ?> allEntries = sharedPreferences.getAll();
            String player1 = selectedPlayersPreferences.getString("Player0", "");
            String player2 = selectedPlayersPreferences.getString("Player1", "");
            currentPlayer1TextView.setText(player1);
            currentPlayer2TextView.setText(player2);
        }


        private void clearSelectedPlayers(){
            SharedPreferences.Editor editor = selectedPlayersPreferences.edit();
            editor.clear();
            editor.apply(); // or editor.commit();
            //set the views back to normal
            currentPlayer1TextView.setText("");
            currentPlayer2TextView.setText("");
        }


    // This method is called to inflate the menu resource file
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu); // Assuming you have a menu resource file named main_menu.xml
        return true;
    }

    // This method is called when a menu item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item clicks here
        int id = item.getItemId();

        // Check which menu item was clicked
        switch (id) {
            case R.id.clearPlayersSelectedMenuButton:
                clearSelectedPlayers();
                Toast.makeText(this, "Players unselected", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
//end MainActivity