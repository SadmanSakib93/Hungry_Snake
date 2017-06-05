package com.example.user.hungrysnake;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class IfHighScore extends Activity {

    EditText name;
    TextView EnterName;
    Button save;
    int sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.if_high_score_activity);
        name = (EditText) findViewById(R.id.EnterNameEditText);
        save = (Button) findViewById(R.id.SaveButton);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highScoreNameInput();
            }
        });


    }


    public void highScoreNameInput() {
        BufferedWriter nameBufferedWriter = null;
        BufferedWriter scoreBufferedWriter = null;

        try {
            FileOutputStream nameFileOutputStream = openFileOutput("NameFile", Context.MODE_PRIVATE);
            nameBufferedWriter = new BufferedWriter(new OutputStreamWriter(nameFileOutputStream));
            nameBufferedWriter.write(name.getText().toString());

            FileOutputStream scoreFileOutputStream = openFileOutput("ScoreFile", Context.MODE_PRIVATE);
            scoreBufferedWriter = new BufferedWriter(new OutputStreamWriter(scoreFileOutputStream));
            sc = FairClass.points;
            String t = Integer.toString(sc);

            scoreBufferedWriter.write(t);
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            try {
                nameBufferedWriter.close();
                scoreBufferedWriter.close();

            } catch (IOException e) {

            }
            Toast.makeText(getBaseContext(), "NEW HIGH SCORE SAVED.",
                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(IfHighScore.this, MainActivity.class));

        }

    }

}
