package com.example.tatyana.counterandroidst;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Tatyana on 2016-08-22.
 *
 */

/**
 * This class contain timer activity
 */

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class Timer_Activity extends Activity {

    private Button start;

    private Button stop;

    private TextView timer;

    private long startTime = 0L;

    private Handler customHandler = new Handler();


    private int c_secs ;
    private int c_mins ;
    private int c_hour;

    long timeInMilliseconds = 0L;


    @Override
    /**
     * Create timer activity
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_layout);

        timer = (TextView) findViewById(R.id.timer);
        start= (Button) findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            /**
             * Start timer
             * @param view
             */
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
            }
        });

       stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            /**
             * Stop timer and send hour and minute to the main activity
             * @param view
             */
            public void onClick(View view) {
                Intent intent = new Intent(Timer_Activity.this, MainActivity.class);
                intent.putExtra("hour",c_hour );
                intent.putExtra("minute", c_mins);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }



    public Runnable updateTimerThread = new Runnable() {
        /**
         * Run and view timer
         */
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            c_secs = (int) (timeInMilliseconds / 1000);
            c_mins = c_secs / 60;
            c_hour = c_mins/60;
            c_secs = c_secs % 60;
            c_mins = c_mins % 60;
            timer.setText("" + String.format("%02d", c_hour) + ":"
                            + String.format("%02d", c_mins) + ":"
                            + String.format("%02d", c_secs));
            customHandler.postDelayed(this, 0);
        }
    };

}


