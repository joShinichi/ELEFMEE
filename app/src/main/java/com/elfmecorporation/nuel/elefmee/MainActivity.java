package com.elfmecorporation.nuel.elefmee;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread t=new Thread()
        {

            public void run()
            {

                try {

                    sleep(6000);
                    finish();
                    Intent cv=new Intent(MainActivity.this,LoginActivity.class/*otherclass*/);
                    startActivity(cv);
                }

                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    
}
