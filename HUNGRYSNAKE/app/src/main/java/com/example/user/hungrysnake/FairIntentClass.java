package com.example.user.hungrysnake;

import android.app.Activity;
import android.os.Bundle;

public class FairIntentClass extends Activity {
    FairClass fairClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fairClass = new FairClass(this);
        setContentView(fairClass);


    }

    @Override
    protected void onPause() {
        super.onPause();
        fairClass.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fairClass.resume();
    }
}
