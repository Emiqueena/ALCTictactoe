package com.example.android.alcnew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {

    int playerNumber;
    int activityNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    public void chooseX(View view){

        playerNumber= 1;

    }

    public void chooseO(View view){
        playerNumber = 0;
    }

    public void choose3(View view){
       activityNumber = 3;
    }

    public void choose5(View view){

        activityNumber = 5;
    }

    public void twoPlayers(View view){

        if(activityNumber==3) {
            Intent playIntent = new Intent(this, MainActivity.class);
            playIntent.putExtra("key", playerNumber);
            startActivity(playIntent);
        }else {

            Intent playIntent = new Intent(this, FiveBoard.class);
            playIntent.putExtra("key", playerNumber);
            startActivity(playIntent);
        }
    }
}
