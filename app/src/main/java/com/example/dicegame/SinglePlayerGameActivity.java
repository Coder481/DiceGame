package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.dicegame.databinding.ActivitySinglePlayerGameBinding;

import java.util.Random;

public class SinglePlayerGameActivity extends AppCompatActivity {

    private static final Random RANDOM = new Random();
    private ActivitySinglePlayerGameBinding b;
    private int chance = 5;
    private AlertDialog resultDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivitySinglePlayerGameBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setUpRollBtn();
    }

    private void setUpRollBtn() {
        b.rollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chance <= 5 && chance>1){
                    int value1 = randomDiceValue();
                    int value2 = randomDiceValue();
                    int res1 = getResources().getIdentifier("dice_"+value1,"drawable","com.example.dicegame");
                    int res2 = getResources().getIdentifier("dice_"+value2,"drawable","com.example.dicegame");
                    b.imageView1.setImageResource(res1);
                    b.imageView2.setImageResource(res2);
                    chance = chance - 1;
                    b.chanceLeftTextView.setText(chance+" chance left");
                    if (value1 == value2){
                        showResultDialog("Hurray! You Win","Play Again");
                    }
                }else{
                    showResultDialog("OOPS! You Loss", "Try Again");
                }

            }
        });
    }

    private void showResultDialog(String status, String btnText) {
        resultDialog = new AlertDialog.Builder(this)
                .setTitle(status)
                .setCancelable(false)
                .setPositiveButton(btnText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rstGame();
                    }
                })
                .show();
    }

    private void rstGame() {
        chance = 5;
        b.chanceLeftTextView.setText(chance+" chance left");
        b.imageView1.setImageDrawable(null);
        b.imageView2.setImageDrawable(null);
        if (resultDialog!=null){
            resultDialog.dismiss();
        }
    }

    private int randomDiceValue() {
        return RANDOM.nextInt(6)+1;
    }
}