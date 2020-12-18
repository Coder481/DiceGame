package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dicegame.databinding.ActivitySelectGameModeBinding;

public class SelectGameModeActivity extends AppCompatActivity {

    private ActivitySelectGameModeBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivitySelectGameModeBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setSinglePlyrBtn();
        setDoublePlyrBtn();

    }

    private void setDoublePlyrBtn() {
        b.doublePlayerGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectGameModeActivity.this,DoublePlayerInfoActivity.class));
            }
        });
    }

    private void setSinglePlyrBtn() {
        b.singlePlayerGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectGameModeActivity.this,SinglePlayerGameActivity.class));
            }
        });
    }
}