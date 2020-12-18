package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dicegame.databinding.ActivitySinglePlayerInfoBinding;

public class DoublePlayerInfoActivity extends AppCompatActivity {

    private ActivitySinglePlayerInfoBinding b;
    private int winningPoint;
    private String player1Name;
    private String player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivitySinglePlayerInfoBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setUpContinueBtn();
    }

    private void setUpContinueBtn() {
        b.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWinningPointFromEditText();
            }
        });
    }

    private void getWinningPointFromEditText() {

        String winPnt = b.winningPointEditText.getText().toString().trim();
        if ( winPnt.length()==0  ){
            b.winningPointEditText.setError("Winning Points can't be empty or 0");
        }else {
            winningPoint = Integer.parseInt(winPnt);
            if (winningPoint == 0){
                b.winningPointEditText.setError("Winning Points can't be empty or 0");
            }else{
                player1Name = b.player1EditText.getText().toString().trim();
                player2Name = b.player2EditText.getText().toString().trim();
                if (player1Name.length() > 8){
                    b.player1EditText.setError("Player name should not exceed 8 characters");
                }else if (player2Name.length() > 8){
                    b.player2EditText.setError("Player name should not exceed 8 characters");
                }else{
                    startGameActivity();
                }
            }
        }
    }

    private void startGameActivity() {
        startActivity(new Intent(DoublePlayerInfoActivity.this,MainActivity.class)
        .putExtra("Player1Name",player1Name)
        .putExtra("Player2Name",player2Name)
        .putExtra("WinningPoint",winningPoint));
    }
}