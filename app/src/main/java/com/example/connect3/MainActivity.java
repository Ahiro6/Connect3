package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Dictionary;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    static boolean turn;
    static Dictionary board;

    static TextView display;
    static int moveCounter;
    static boolean gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
    }

    public void setup() {
        board = new Hashtable();

        gameOver = false;
        turn = true;
        moveCounter = 0;
        display = (TextView) findViewById(R.id.display);
        display.setText("The winner is ...");

        for(int i = 1; i <= 9; i++) {
            Button button = (Button) findViewById(getResources().getIdentifier("button" + i, "id", this.getPackageName()));
            button.setBackgroundColor(getResources().getColor(R.color.plain));
            button.setEnabled(true);
        }
    }

    public void play(View view) {
        if(gameOver) return;

        Button button = (Button) view;

        String player = turn ? "red" : "blue";

        button.setBackgroundColor(turn ? getResources().getColor(R.color.red_team) : getResources().getColor(R.color.blue_team));
        board.put(button.getId(), player);

        button.setEnabled(false);
        turn = !turn;
        moveCounter++;

        String winner = checkWinner(player);
        if(winner != null) {
            endGame(winner);
        }

    }

    public String checkWinner(String team) {
        if(board.get(R.id.button1) == team && board.get(R.id.button2) == team && board.get(R.id.button3) == team) {
            return team;
        }

        if(board.get(R.id.button4) == team && board.get(R.id.button5) == team && board.get(R.id.button6) == team) {
            return team;
        }

        if(board.get(R.id.button7) == team && board.get(R.id.button8) == team && board.get(R.id.button9) == team) {
            return team;
        }

        if(board.get(R.id.button1) == team && board.get(R.id.button4) == team && board.get(R.id.button7) == team) {
            return team;
        }

        if(board.get(R.id.button2) == team && board.get(R.id.button5) == team && board.get(R.id.button8) == team) {
            return team;
        }

        if(board.get(R.id.button3) == team && board.get(R.id.button6) == team && board.get(R.id.button9) == team) {
            return team;
        }

        if(board.get(R.id.button1) == team && board.get(R.id.button5) == team && board.get(R.id.button9) == team) {
            return team;
        }

        if(board.get(R.id.button3) == team && board.get(R.id.button5) == team && board.get(R.id.button7) == team) {
            return team;
        }

        if(moveCounter == 9) {
            return "draw";
        }

        return null;
    }

    public void endGame(String player) {
        gameOver = true;
        if(player.equals("draw")) {
            display.setText("It is a draw!!!");
            return;
        }
        display.setText("The winner is " + player);

    }

    public void restart(View view) {
        setup();
    }
}