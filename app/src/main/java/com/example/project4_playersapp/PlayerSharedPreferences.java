package com.example.project4_playersapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Map;


/*
********************************************************************************************
********************************************************************************************
FOR REFERENCE ONLY THIS CODE IS NOT USED AT ALL BUT SHOWS SOME IMPORTANT INFORMATION

SEE THE ADDPLAYER.JAVA FILE TO SEE THE SHARED PREFERENCE USED
********************************************************************************************
********************************************************************************************
 */

//Save the information through SharedPreferences
public class PlayerSharedPreferences {

    private static final String PLAYER_PREFERENCES = "PlayerPrefs";
    private static final String KEY_PLAYER_PREFIX = "player_";
    private SharedPreferences sharedPreferences;

    public PlayerSharedPreferences(Context context){
        sharedPreferences = context.getSharedPreferences(PLAYER_PREFERENCES, Context.MODE_PRIVATE);
    }

    //Save the players name into preference
    public void savePlayerName(int playerId, String playerName){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String key = KEY_PLAYER_PREFIX + playerId;
        editor.putString(KEY_PLAYER_PREFIX + playerId, playerName);
        editor.apply();
    }//end savePlayerName

    public String getPlayerName(int playerId){
        return sharedPreferences.getString(KEY_PLAYER_PREFIX + playerId, "Player"); //"Player" will be replaced.
    }//end getPlayerName

    //Gets players count
    public int getPlayerSize(){
        //getAll() returns all values that must be assigned to Map
        //<String, ?> ? is a wildcard able to receive anything.
        try{
            Map<String, ?> playersMap = sharedPreferences.getAll();
            return playersMap.size();
        }
        catch (Exception e){
            return 0;
        }
    }

    //Clear the SP - might be unfinished
    public void clearSP(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply(); // or editor.commit();

    }
}//end PlayerSharedPreferences
