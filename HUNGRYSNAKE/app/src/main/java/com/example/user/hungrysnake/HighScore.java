package com.example.user.hungrysnake;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class HighScore extends Activity {
    TextView nameText, scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_score_activity);
        nameText = (TextView) findViewById(R.id.NameText);
        scoreText = (TextView) findViewById(R.id.ScoreText);


        BufferedReader nameBufferedReader = null;
        BufferedReader scoreBufferedReader = null;
        StringBuilder nameStringbuilder = new StringBuilder();
        StringBuilder scoreStringbuilder = new StringBuilder();
        try {
            FileInputStream nameFileInputStream = openFileInput("NameFile");
            nameBufferedReader = new BufferedReader(new InputStreamReader(nameFileInputStream));
            String name_string;
            while ((name_string = nameBufferedReader.readLine()) != null) {
                nameStringbuilder.append("> " + name_string + "\r\n");


                FileInputStream scoreFileInputStream = openFileInput("ScoreFile");
                scoreBufferedReader = new BufferedReader(new InputStreamReader(scoreFileInputStream));
                String score_string;
                while ((score_string = scoreBufferedReader.readLine()) != null) {
                    scoreStringbuilder.append(score_string + "\r\n");
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            try {
                nameBufferedReader.close();
                scoreBufferedReader.close();
            } catch (IOException e) {

            }
            nameText.setText(nameStringbuilder);
            scoreText.setText(scoreStringbuilder);
        }

    }


}
