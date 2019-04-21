package com.example.echo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView echoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        echoText = (TextView) findViewById(R.id.echoText);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/lucida_caligraphy.ttf");

        echoText.setTypeface(custom_font);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }

    public void onSignToTextClicked(View v){
        Intent intent = new Intent(MainActivity.this, SignToText.class);
        startActivity(intent);
    }

    public void onLearnClicked(View v){
        Intent intent = new Intent(MainActivity.this, LearnMenu.class);
        startActivity(intent);
    }

    public void onTextToSpeechClicked(View v){
        Intent intent = new Intent(MainActivity.this, TextToSpeech.class);
        startActivity(intent);
    }

    public void onSpeechToTextClicked(View v){
        Intent intent = new Intent(MainActivity.this, SpeechToText.class);
        startActivity(intent);
    }

    public void onTextToSignClicked(View v){
        Intent intent = new Intent(MainActivity.this, TextToSign.class);
        startActivity(intent);

    }

    public void onSpeechToSignClicked(View v){
        Intent intent = new Intent(MainActivity.this, SpeechToSign.class);
        startActivity(intent);
    }

    public void onSignToSpeechClicked(View v){
        Intent intent = new Intent(MainActivity.this, SignToText.class);
        intent.putExtra("outCheck", true);
        startActivity(intent);
    }
}
