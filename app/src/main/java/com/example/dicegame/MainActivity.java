package com.example.dicegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dicegame.databinding.ActivityMainBinding;
import com.example.dicegame.databinding.WinningPointDialogLayoutBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding b;
    private static final Random RANDOM = new Random();
    public int chance = 0;
    public int aScore = 0 , bScore = 0 ;
    private AlertDialog winnerDialog;
    private WinningPointDialogLayoutBinding winningDialogBinding;
    private AlertDialog pointsDialog;
    private int winningPoint;
    private String player1Name;
    private String player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        Intent intent = getIntent();
        player1Name = intent.getStringExtra("Player1Name");
        player2Name = intent.getStringExtra("Player2Name");
        winningPoint = intent.getIntExtra("WinningPoint",20);

        setInitialViews();
        setRollBtn();
    }

    private void setInitialViews() {
        b.player1NameTextView.setText(player1Name+"");
        b.player2NameTextView.setText(player2Name+"");
        b.resultTextView.setText(player1Name+"'s chance");
        b.resultTextView.setTextColor(Color.RED);
    }


    private void setRollBtn() {
        b.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = randomDiceValue();
                int res = getResources().getIdentifier("dice_"+value,"drawable","com.example.dicegame");
                b.imageView.setImageResource(res);
                if (chance % 2 == 0){
                    chance = chance + 1;
                    aScore = aScore + value;
                    b.aScoreTextView.setText(""+aScore);
                    if (aScore>=winningPoint){
                        showWinnerDialog("Hurray! "+player1Name+" won the match");
                    }
                    b.resultTextView.setText(player2Name+"'s chance");
                    b.resultTextView.setTextColor(Color.GREEN);

                }else {
                    chance = chance + 1;
                    bScore = bScore + value;
                    b.bScoreTextView.setText(""+bScore);
                    if (bScore>=winningPoint){
                        showWinnerDialog("Hurray! "+player2Name+" won the match");
                    }
                    b.resultTextView.setText(player1Name+"'s chance");
                    b.resultTextView.setTextColor(Color.RED);
                }
            }
        });
    }

    private void showWinnerDialog(String winner) {
        winnerDialog = new AlertDialog.Builder(this)
                .setTitle(winner)
                .setCancelable(false)
                .setPositiveButton("RESET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetGame();
                    }
                })
                .show();
    }

    private int randomDiceValue() {
        return RANDOM.nextInt(6)+1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.info_btn){
            new AlertDialog.Builder(this)
                    .setMessage("Player who score "+ winningPoint +" points first will win the game")
                    .show();
        }else if (item.getItemId() == R.id.reset_btn){
            askForConfirmation();
        }
        return super.onOptionsItemSelected(item);
    }

    private void askForConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Reset Game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetGame();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "OK! Enjoy your game", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void resetGame() {
        if (winnerDialog !=null){
            winnerDialog.dismiss();
        }
        chance = 0;
        aScore = 0;
        bScore = 0;
        b.aScoreTextView.setText("0");
        b.bScoreTextView.setText("0");
        b.resultTextView.setText(player1Name+"'s chance");
        b.resultTextView.setTextColor(Color.RED);
    }
}