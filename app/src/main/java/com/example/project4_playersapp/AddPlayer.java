package com.example.project4_playersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class AddPlayer extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private EditText addNewPlayerEditTextText;
    private Button addNewPlayerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        addNewPlayerEditTextText = (EditText) findViewById(R.id.addNewPlayerEditTextText);
        addNewPlayerButton = (Button) findViewById(R.id.addNewPlayerButton);

        //Initialize shared preferences
        sharedPreferences = getSharedPreferences("PlayerPrefs", Context.MODE_PRIVATE);

        addNewPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPlayerName = addNewPlayerEditTextText.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Map<String, ?> playerMap = sharedPreferences.getAll(); //assign values to map to count next
                int playerSize = playerMap.size(); //get size of how many players there are

                editor.putString("player_" + playerSize, newPlayerName);
                editor.apply();
                Toast.makeText(AddPlayer.this, newPlayerName + " Added!", Toast.LENGTH_SHORT).show();

                updateAndDisplay();

            }
        });
    }

    public void updateAndDisplay(){
        //Put screen back to normal
        addNewPlayerEditTextText.setText("");
    }
}