package com.example.user.hungrysnake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import static android.view.View.*;

public class LevelClass extends Activity {

    ImageButton FAIR, INSANE, IMPOSSIBLE;
    static int Level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_layout);
        FAIR = (ImageButton) findViewById(R.id.fair);
        INSANE = (ImageButton) findViewById(R.id.insane);
        IMPOSSIBLE = (ImageButton) findViewById(R.id.impossible);

        FAIR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelClass.this, FairIntentClass.class));
                Level = 1;


            }
        });

        INSANE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LevelClass.this, FairIntentClass.class));
                Level = 2;

            }
        });
        IMPOSSIBLE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LevelClass.this, FairIntentClass.class));
                Level = 3;

            }
        });

    }

}

