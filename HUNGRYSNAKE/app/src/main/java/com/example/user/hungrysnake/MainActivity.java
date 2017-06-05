package com.example.user.hungrysnake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;


public class MainActivity extends Activity {

    ImageButton newGame, help, highScore, quit;
    RelativeLayout relativeLayout;
    static int currentHighScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.relative);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slidedown);
        relativeLayout.startAnimation(animation);

        getCurrentHighScore();


        newGame = (ImageButton) findViewById(R.id.playButton);
        Animation animationPlay = AnimationUtils.loadAnimation(MainActivity.this, R.anim.trans);
        newGame.startAnimation(animationPlay);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.LEVELCLASS"));

            }
        });

        highScore = (ImageButton) findViewById(R.id.highScoreButton);
        Animation animationhighScore = AnimationUtils.loadAnimation(MainActivity.this, R.anim.trans);
        highScore.startAnimation(animationhighScore);
        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.HIGHSCORE"));
            }
        });

        help = (ImageButton) findViewById(R.id.HelpButton);
        Animation animationhelp = AnimationUtils.loadAnimation(MainActivity.this, R.anim.trans);
        help.startAnimation(animationhelp);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.HELP"));

            }
        });

        quit = (ImageButton) findViewById(R.id.QuitButton);
        Animation animationquit = AnimationUtils.loadAnimation(MainActivity.this, R.anim.trans);
        quit.startAnimation(animationquit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit(0);

            }
        });
    }


    public void getCurrentHighScore() {
        BufferedReader scoreBufferedReader = null;
        try {
            FileInputStream scoreFileInputStream = openFileInput("ScoreFile");
            scoreBufferedReader = new BufferedReader(new InputStreamReader(scoreFileInputStream));
            String scoreString;
            scoreString = scoreBufferedReader.readLine();
            if (scoreString != null) {
                currentHighScore = Integer.parseInt(scoreString);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            try {
                scoreBufferedReader.close();
            } catch (IOException e) {

            }

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
