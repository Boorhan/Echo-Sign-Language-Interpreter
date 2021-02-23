package com.example.echo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class TextToSign extends Activity  {
    public static final String TAG = "tts";

    private EditText entertext;
    private ImageView imagev;
    private List<StringBuffer> mURLs = new LinkedList<StringBuffer>();
    private Bitmap bitmap;
    private StringBuffer url;
    private String text = "";
    boolean signComeCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "tts activity started");
        setContentView(R.layout.activity_text_to_sign);
        entertext = (EditText) findViewById(R.id.editTextTTS);
        entertext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    onSubmitClicked(v);
                }
                return false;
            }
        });
        try {
            text = getIntent().getExtras().getString("text");

            if(!text.equals("")){
                imagev = (ImageView) findViewById(R.id.signImageViewTTS);
                ((ViewGroup) entertext.getParent()).removeView(entertext);
                Button submitButton = findViewById(R.id.submitTTS);
                ((ViewGroup) submitButton.getParent()).removeView(submitButton);
                processText(text);
            }


        }catch (Exception e){

        }

    }

    public void onSubmitClicked(View v) {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        Toast.makeText(getApplicationContext(), "Processing...message_processing", Toast.LENGTH_SHORT).show();

        /* ImageView which shows the result images*/
        imagev = (ImageView) findViewById(R.id.signImageViewTTS);

        String strtxt = entertext.getText().toString();
//        Log.d(TAG, "onSubmitClicked: "+strtxt);
        if (strtxt.equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter Somthing", Toast.LENGTH_SHORT).show();
            return;
        }
        processText(strtxt);
    }

    private void processText(String strtxt) {
//        Log.d(TAG, "processText: entered");
        /* Split the input sentence into words */
        String[] words = strtxt.split("\\s+");

        /*Remove special characters and convert uppercase characters to lowercase characters*/
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[^\\w]", "");
            words[i] = words[i].toLowerCase();
        }

        /*Create a URL for each word and it to the URLs list*/
        for (int i = 0; i < words.length; i++) {
            /*
             * "http://www.lifeprint.com/asl101/pages-signs/" + FIRST_CHAR + WORD + ".htm" has images for WORD
             * */
            url = new StringBuffer("http://www.lifeprint.com/asl101/pages-signs/");
            url.append(words[i].charAt(0));
            url.append('/');
            url.append(words[i]);
            url.append(".htm");
            mURLs.add(url);
        }
        try {
//            Log.d(TAG, "processText: "+mURLs.toString());
            loadNext();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void loadNext() throws InterruptedException, ExecutionException {
        if (mURLs.isEmpty()) {
            if(!text.equals("")){
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(TextToSign.this, SpeechToSign.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }
                }, 1500);
            }

            return;
        }
        url = mURLs.remove(0);
        if (url != null) {
            new LoadImage().execute();
        }
    }

    private class LoadImage extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... args) {

            /* if url is URL of number/alphabet */
            Log.d(TAG, "doInBackground: "+url.toString());
            if(url.indexOf("numbers")>=0 || url.indexOf("fingerspelling")>=0){
                Log.d(TAG, "doInBackground: this is a number or alphabet");
                try {
                    bitmap = BitmapFactory.decodeStream((InputStream) new URL(url.toString()).getContent());

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            /* if url is URL of a word */

            else {
                try {

                    /* Connect to the website using Jsoup and retrieve the first "jpg" image as Bitmap*/

                    Document doc = Jsoup.connect(url.toString()).ignoreContentType(true).get();
                    Element img = doc.select("img[src$=.jpg]").first();
                    String Str = img.attr("abs:src");
                    bitmap = BitmapFactory.decodeStream((InputStream) new URL(Str).getContent());
                }
                catch (UnknownHostException e) {

                    /* UnKnownHostException raised when there is no internet */
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(TextToSign.this, "No internet", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                catch (Exception e) {

                    /* Exception is raised when the word is not there in lifeprint.com */
                    bitmap = null;
                    String t = url.toString();

                    /* Get the word from the URL */
                    String str = t.substring(46, t.lastIndexOf('.'));

                    /* Traverse the word from last and add URL for each character to the beginning of URLs list*/

                    for (int i = str.length() - 1; i >= 0; i--) {

                        /* If the character is a number use "http://www.lifeprint.com/asl101/signjpegs/numbers/number0" and add it to the beginning of URLs list */

                        if (str.charAt(i) <= '9' && str.charAt(i) >= '0')
                            mURLs.add(0, new StringBuffer("http://www.lifeprint.com/asl101/signjpegs/numbers/number0" + str.charAt(i) + ".jpg"));

                            /* If the character is an alphabet use "http://www.lifeprint.com/asl101/fingerspelling/abc-gifs/" and add it to the beginning of URLs list */
                        else
                            mURLs.add(0, new StringBuffer("http://www.lifeprint.com/asl101/fingerspelling/abc-gifs/" + str.charAt(i) + "_small.gif"));
                    }

                    /* Remove the newly added character URL and get the image as bitmap */
                    StringBuffer url = mURLs.remove(0);
                    try {
                        bitmap = BitmapFactory.decodeStream((InputStream) new URL(url.toString()).getContent());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            //CHANGING SIGN


            /* The bitmap is set to ImageView onPost downloading and loadNext method is called for next URL in the URLs list*/
            if (bitmap != null) {
                imagev.setImageBitmap(bitmap);
                imagev.setAlpha(1f);
                Animation fadeOut = new AlphaAnimation(1, .3f);
                fadeOut.setInterpolator(new AccelerateInterpolator());
                fadeOut.setDuration(200);


                fadeOut.setAnimationListener(new Animation.AnimationListener()
                {
                    public void onAnimationEnd(Animation animation)
                    {
                        imagev.setAlpha(.3f);
                    }
                    public void onAnimationRepeat(Animation animation) {}
                    public void onAnimationStart(Animation animation) {}
                });
                fadeOut.setStartOffset(300);
                imagev.startAnimation(fadeOut);

            }
            try {
                loadNext();
            } catch (InterruptedException e) {
                Log.i("ahtrap", "ended1");
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                Log.i("ahtrap","ended2");
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
