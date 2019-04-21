package com.example.echo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class TextToSpeech extends AppCompatActivity {

    private EditText inputText;
    private String text;
    private Button speakButton;
    private android.speech.tts.TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        inputText = (EditText) findViewById(R.id.editTextTTT);
        speakButton = (Button) findViewById(R.id.speakButtonTTT);

        mTTS = new android.speech.tts.TextToSpeech(this, new android.speech.tts.TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == android.speech.tts.TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.GERMAN);

                    if (result == android.speech.tts.TextToSpeech.LANG_MISSING_DATA
                            || result == android.speech.tts.TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        speakButton.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
    }

    public void onSpeakClicked(View v){
        text = inputText.getText().toString();
        mTTS.speak(text, android.speech.tts.TextToSpeech.QUEUE_FLUSH, null);
    }
}
