package com.example.echo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class SignToText extends AppCompatActivity {
    Button a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,nice,house,you,teacher,ok,space, cross, signAudio;
    TextView textView;
    String str="";
    Boolean outCheck = false;
    android.speech.tts.TextToSpeech mTTS;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignToText.this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_to_text);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mTTS = new android.speech.tts.TextToSpeech(this, new android.speech.tts.TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == android.speech.tts.TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.GERMAN);

                    if (result == android.speech.tts.TextToSpeech.LANG_MISSING_DATA
                            || result == android.speech.tts.TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
        signAudio = (Button) findViewById(R.id.signToAudioButton);
        try {
            outCheck = getIntent().getExtras().getBoolean("outCheck");
        } catch (Exception e){

        }
        if(outCheck == false){
            ((ViewGroup) signAudio.getParent()).removeView(signAudio);
        }
        cross = (Button) findViewById(R.id.crossButton);
        a=(Button)findViewById(R.id.buttonA);
        b=(Button)findViewById(R.id.buttonB);
        c=(Button)findViewById(R.id.buttonC);
        d=(Button)findViewById(R.id.buttonD);
        e=(Button)findViewById(R.id.buttonE);
        f=(Button)findViewById(R.id.buttonF);
        g=(Button)findViewById(R.id.buttonG);
        h=(Button)findViewById(R.id.buttonH);
        i=(Button)findViewById(R.id.buttonI);
        j=(Button)findViewById(R.id.buttonJ);
        k=(Button)findViewById(R.id.buttonK);
        l=(Button)findViewById(R.id.buttonL);
        m=(Button)findViewById(R.id.buttonM);
        n=(Button)findViewById(R.id.buttonN);
        o=(Button)findViewById(R.id.buttonO);
        p=(Button)findViewById(R.id.buttonP);
        q=(Button)findViewById(R.id.buttonQ);
        r=(Button)findViewById(R.id.buttonR);
        s=(Button)findViewById(R.id.buttonS);
        t=(Button)findViewById(R.id.buttonT);
        u=(Button)findViewById(R.id.buttonU);
        v=(Button)findViewById(R.id.buttonV);
        w=(Button)findViewById(R.id.buttonW);
        x=(Button)findViewById(R.id.buttonX);
        y=(Button)findViewById(R.id.buttonY);
        z=(Button)findViewById(R.id.buttonZ);
        nice=(Button)findViewById(R.id.Nice);
        teacher=(Button)findViewById(R.id.teacher);
        you=(Button)findViewById(R.id.You);
        house=(Button)findViewById(R.id.House);
        ok=(Button)findViewById(R.id.OK);
        space=(Button)findViewById(R.id.Space);
        textView=(TextView)findViewById(R.id.textView);
        a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "j";
                textView.setText(str);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "i";
                textView.setText(str);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "h";
                textView.setText(str);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "g";
                textView.setText(str);
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "f";
                textView.setText(str);
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "e";
                textView.setText(str);
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "d";
                textView.setText(str);
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "c";
                textView.setText(str);
            }
        });
        i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "b";
                textView.setText(str);
            }
        });
        j.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "a";
                textView.setText(str);
            }
        });
        k.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "s";
                textView.setText(str);
            }
        });
        l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "r";
                textView.setText(str);
            }
        });
        m.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "q";
                textView.setText(str);
            }
        });
        n.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "p";
                textView.setText(str);
            }
        });
        o.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "o";
                textView.setText(str);
            }
        });
        p.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "n";
                textView.setText(str);
            }
        });
        q.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "m";
                textView.setText(str);
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "l";
                textView.setText(str);
            }
        });
        s.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "k";
                textView.setText(str);
            }
        });
        t.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "z";
                textView.setText(str);
            }
        });
        u.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "y";
                textView.setText(str);
            }
        });
        v.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "x";
                textView.setText(str);
            }
        });
        w.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "w";
                textView.setText(str);
            }
        });
        x.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "v";
                textView.setText(str);
            }
        });
        y.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "u";
                textView.setText(str);
            }
        });
        z.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "t";
                textView.setText(str);
            }
        });
        nice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ " nice";
                textView.setText(str);
            }
        });
        teacher.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ "teacher";
                textView.setText(str);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ " okay";
                textView.setText(str);
            }
        });
        house.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ " house";
                textView.setText(str);
            }
        });
        you.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ " you";
                textView.setText(str);
            }
        });
        space.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                str= str+ " ";
                textView.setText(str);
            }
        });
        cross.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (str != null && str.length() > 0) {
                    str = str.substring(0, str.length() - 1);
                }
                textView.setText(str);
            }
        });

        signAudio.setOnClickListener( v-> {
            if(str.isEmpty()) return;
            mTTS.speak(str, android.speech.tts.TextToSpeech.QUEUE_FLUSH, null);
        });
    }
}
