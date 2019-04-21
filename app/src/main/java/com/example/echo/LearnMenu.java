package com.example.echo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LearnMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void onLearnAlphabetClicked(View v){
        startActivity(new Intent(LearnMenu.this, Alphabet.class));
    }

    public void onLearnNumberClicked(View v){
        startActivity(new Intent(LearnMenu.this, Number.class));
    }

    public void onFrequentlyUsedClicked(View v){
        startActivity(new Intent(LearnMenu.this, FrequentlyUsed.class));
    }
}
