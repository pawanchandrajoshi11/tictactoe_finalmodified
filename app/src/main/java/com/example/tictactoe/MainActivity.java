package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;           //base class for using new devices features on older android devices like toolbar api

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;                                                                        //for toast class

import java.util.Arrays;                                                                            //for implementing array class

public class MainActivity extends AppCompatActivity {                                               //inheritance
    boolean gameActive = true;                                                                //boolean variable for checking winning states
    // INFO ABOUT PLAYERS...        // 0 --> X (crosses)
                                    // 1 --> O (noughts)

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //    ALL TYPES OF GAMESTATES...
                                    //    0 --> X (crosses)
                                    //    1 --> O (noughts)
                                    //    2 --> Null
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}}; //all possible winning states

    @SuppressLint("SetTextI18n")
    public void playertap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!gameActive) {      //if gameactive is false then the game will automatically reset.
            gameReset(view);                                                                        //for new match
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {                                                                //conditions for taking user input in blank spaces
                img.setImageResource(R.drawable.x);                                                 //for X
                activePlayer = 1;
                TextView status;
                status = findViewById(R.id.status);
                status.setText("O's Turn!, good luck");
            } else {                                                                                //conditions for taking user input in blank spaces
                img.setImageResource(R.drawable.o);                                                 //for O
                activePlayer = 0;
                TextView status;
                status = findViewById(R.id.status);
                status.setText("X's Turn!, good luck");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //To Check whether any player has won or is it a DRAW?
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {                           //this is to ensure that my winposition is not equals to NULL
                // We got a winner - but who?
                String winnerStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {                                               //winning condition
                    winnerStr = "X is the Winner!";
                } else {
                    winnerStr = "O is the Winner!";
                }
                // Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

            }

        }
        boolean emptySquare = false;
        for (int squareState : gameState) {
                                        //loop for traversing in gameState array, if any element is NULL then boolean emptySquare will be true
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }
        if (!emptySquare && gameActive) {               //condition for DRAW, if emptySquare is false which means it doesn't contain NULL space
                                                        //and gameActive is also true then this means the match is draw!!!
            gameActive = false;
            String winnerStr;
            winnerStr = "DRAW!!! No one won";
            TextView status = findViewById(R.id.status);                                            //printing the final status.
            status.setText(winnerStr);
        }
    }
    @SuppressLint("SetTextI18n")
    public void gameReset(View view) {                                  //definition of gameReset(), this will assign all imageResources to 0
        gameActive = true;
        activePlayer = 0;
        Arrays.fill(gameState, 2);
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn!, good luck");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Best of Luck!", Toast.LENGTH_LONG).show();                 //toast for pop-up
    }
}



