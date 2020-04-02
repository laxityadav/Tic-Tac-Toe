package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtPlayer1, txtPlayer2;
    private Button buttons[][]= new Button[3][3];
    private boolean player1Turn = true;
    private int round =0;

    private int player1Points;
    private int player2Points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        txtPlayer1 = findViewById(R.id.player1Wins);
        txtPlayer2 = findViewById(R.id.player2Wins);

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                String btnId = "btn_" + i +  j;
                int resId = getResources().getIdentifier(btnId, "id", getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(!(((Button) view).getText().toString().equals(""))) {
            return;
        }

        if(player1Turn) {
            ((Button) view).setText("X");
        }
        else {
             ((Button) view).setText("O");
        }

        round++;
        if(checkForWin()) {
            if(player1Turn) {
                Toast.makeText(this, "player 1 Wins", Toast.LENGTH_SHORT).show();
                player1Points++;
            }
            else {
                Toast.makeText(this, "player 2 Wins", Toast.LENGTH_SHORT).show();
                player2Points++;
            }
            resetGame();
        }
        else if(round == 9) {
            Toast.makeText(this, "It's a Draw", Toast.LENGTH_SHORT).show();
            resetGame();
        }
        else {
            player1Turn = !player1Turn;
        }

        txtPlayer1.setText("Player 1 Wins: "+ player1Points);
        txtPlayer2.setText("Player 2 Wins: "+ player2Points);
    }

    private void resetGame() {

        round = 0;
        player1Turn = true;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    private boolean checkForWin() {

        String [][]str = new String[3][3];
        // Copy values from button to a string str.
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                str[i][j] = buttons[i][j].getText().toString();
            }
        }
        // check win for a column.
        for(int i=0; i<3; i++) {
            if(str[0][i].equals(str[1][i]) && str[1][i].equals(str[2][i]) && !str[0][i].equals("")) {
                return true;
            }
        }
        //check win for a row.
        for(int i=0; i<3; i++) {
            if(str[i][0].equals(str[i][1]) && str[i][1].equals(str[i][2]) && !str[i][0].equals("")) {
                return true;
            }
        }
        //check win for a diagnol.
        if (str[0][0].equals(str[1][1]) && str[0][0].equals(str[2][2]) && !str[0][0].equals("")) {
            return true;
        }

        if (str[0][2].equals(str[1][1]) && str[0][2].equals(str[2][0]) && !str[0][2].equals("")) {
            return true;
        }

        return false;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("round", round);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        savedInstanceState.getInt("round");
        savedInstanceState.getInt("player1Points");
        savedInstanceState.getInt("player2Points");
        savedInstanceState.getBoolean("player1Turn");
    }
}
