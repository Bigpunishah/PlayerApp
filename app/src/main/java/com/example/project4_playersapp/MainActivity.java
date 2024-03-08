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

public class MainActivity extends AppCompatActivity {

    private Button startGameButton;
    private Button viewScoreBoardButton;
    private Button selectPlayerOneButton;
    private Button selectPlayerTwoButton;
    private Button addPlayerButton;
    private TextView currentPlayer1TextView;
    private TextView currentPlayer2TextView;


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

        //Set OnClickListener for the buttons to switch screens
        String player1String = currentPlayer1TextView.getText().toString();
        String player2String = currentPlayer2TextView.getText().toString();

        //Ensure player 1 & 2 have been selected
        if(!player1String.equals("") && !player2String.equals("")){

            //The game can start once inside here
            startGameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Start the game emulator activity
                    Intent intent = new Intent(MainActivity.this, GameEmulator.class);
                    startActivity(intent);
                }
            });
//            Toast.makeText(this, "Player 1 and Player 2 must be selected to play", Toast.LENGTH_SHORT).show(); //currently does not show message


        } else {
            //Show message if players have not been selected
            startGameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Start the game emulator activity
                    Toast.makeText(MainActivity.this, "Player 1 and Player 2 must be selected to play", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("PlayerPrefs", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit(); just for deleting player names
//                    editor.clear();
//                    editor.apply();
                }
            });
        }


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


    }//end onCreate


}//end MainActivity