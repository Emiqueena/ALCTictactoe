package com.example.android.alcnew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] buttons = new Button[3][3];
    private boolean xTurn;
    private int gameCount;
    private int xPoints;
    private int oPoints;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = this.getIntent().getExtras();
        int number = bundle.getInt("key");
        if(number==1){

            xTurn=true;
        }else if(number==0){

            xTurn = false;
        }
        textViewPlayer1 = (TextView) findViewById(R.id.text_view_p1);
        textViewPlayer2 = (TextView) findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID,"id", getPackageName());

                buttons[i][j] = (Button) findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
}

        ImageButton buttonReset = (ImageButton) findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View view) {
        resetGame();
    }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        gameCount++;
        if (xTurn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");

        }

        if (checkForWin()) {
            if (xTurn) {
                player1Wins();
            } else {
                player2Wins();
            }
        }

         else if (gameCount == 9) {
            draw();
        }
            xTurn = !xTurn;


    }
    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        xPoints++;
        Toast.makeText(this, "X wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        xTurn = false;
    }

    private void player2Wins() {
        oPoints++;
        Toast.makeText(this, "O wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        xTurn = true;
    }

    private void draw() {
        Toast.makeText(this, "The game is a Draw!", Toast.LENGTH_SHORT).show();
    }

    private void updatePointsText() {
        textViewPlayer1.setText(" " + xPoints);
        textViewPlayer2.setText(" " + oPoints);
    }

    private void boardReset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        gameCount = 0;
        xTurn = true;
    }

    public void resetBoard(View view){
        boardReset();
    }

    private void resetGame() {
        xPoints = 0;
        oPoints = 0;
        updatePointsText();
        boardReset();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("gameCount", gameCount);
        outState.putInt("xPoints", xPoints);
        outState.putInt("oPoints", oPoints);
        outState.putBoolean("xTurn",xTurn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        gameCount = savedInstanceState.getInt("gameCount");
        xPoints = savedInstanceState.getInt("xPoints");
        oPoints = savedInstanceState.getInt("oPoints");
        xTurn = savedInstanceState.getBoolean("xTurn");
    }
}
