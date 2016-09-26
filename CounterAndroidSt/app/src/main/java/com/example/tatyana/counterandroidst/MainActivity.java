package com.example.tatyana.counterandroidst;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

/**This class contain main activity, where we see all earn and all time in work
 *
 */

public class MainActivity extends AppCompatActivity {

    /**code, who return activity, where closed*/
    final int REQUEST_CODE_TIMER = 1;
    final int REQUEST_CODE_REPLACE = 2;
    final int REQUEST_CODE_ADD = 3;


    private TextView all_time;
    private TextView all_cash;


    private int all_hour;
    private int all_minute;
    private int c_hour;
    private int c_minute;
    private int c_hour_start;
    private int c_minute_start;

    private double suma;
    private double money = 12;

    @Override
    /**Create main activity
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        all_time = (TextView) findViewById(R.id.all_time);
        all_cash = (TextView) findViewById(R.id.all_cash);

    }

    @Override
    /**Create menu (action bar)
     *
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Create Timer Activity
     * @param item - we choose in menu
     */
    public void showTimer_Activity(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_timer)
        {
            Intent intent = new Intent(this, Timer_Activity.class);
            startActivityForResult(intent, REQUEST_CODE_TIMER);

        }
    }

    /**
     * Creare Replace money Activity - this is we can change earn per hour
     * @param item - we choose in menu
     */
    public void showReplace_money_Activity(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_replace_money)
        {
            Intent intent = new Intent(this, Replace_money_Activity.class);
            startActivityForResult(intent, REQUEST_CODE_REPLACE);

        }
    }
    /**
     * Creare Replace Add Activity - this is we write in the box start time work and stop time work
     * @param item - we choose in menu
     */
    public void showAdd_Activity(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_add)
        {
            Intent intent = new Intent(this, Add_Activity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD);

        }
    }

    /**
     * Recieved Activity Result
     * @param requestCode - code we recieved in activity
     * @param resultCode - if result ok, we close another activity with result ok
     * @param data - data , who we recieved another activity
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_TIMER:
                    int hour = data.getIntExtra("hour", 12);
                    int minute = data.getIntExtra("minute", 11);
                    getAlltime(hour, minute);
                    getCash();
                    all_time.setText("" + String.format("%02d", all_hour) + ":"
                            + String.format("%02d", all_minute));
                    all_cash.setText("" + String.format("%.2f", suma));
                    break;
                case REQUEST_CODE_REPLACE:
                    String cash = data.getStringExtra("cash");
                    money = Double.parseDouble(cash);
                    break;
                case REQUEST_CODE_ADD:
                    String start = data.getStringExtra("start");
                    String stop = data.getStringExtra("stop");
                    checkWidth( start);
                    c_hour_start = c_hour;
                    c_minute_start = c_minute;
                    checkWidth( stop);
                    /**we check the right time enter box*/
                    if((c_hour_start <= c_hour) && !((c_hour_start == 0 && c_minute_start == 0) || (c_hour == 0 && c_minute == 0)))
                    {
                        sub(c_hour_start,c_hour, c_minute_start, c_minute);
                    }
                    getCash();
                    all_time.setText("" + String.format("%02d", all_hour) + ":"
                            + String.format("%02d", all_minute));
                    all_cash.setText("" + String.format("%.2f", suma));
                    break;
            }
        }
    }

    /**
     * This function make time and earn == 0
     * Where we would like count again
     * @param item - we choose in menu
     */
    public void deleteTime(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_new_date)
        {
            all_hour = 0;
            all_minute = 0;
            suma = 0.0;

            all_time.setText("" + String.format("%02d",all_hour) + ":"
                    + String.format("%02d", all_minute) );
            all_cash.setText("" + String.format("%.2f",suma));
        }
    }

    /**
     * hour stop substraction hour start, who we enter the box
     * minute stop substraction minute start, who we enter the box
     * @param c_h_start - current hour start
     * @param c_h_stop - current hour stop
     * @param c_m_start - current minute start
     * @param c_m_stop - current minute stop
     */
    private void sub(int c_h_start,int c_h_stop, int c_m_start, int c_m_stop)
    {
        int hour = 0;
        int min = 0;

        hour = c_h_stop-c_h_start;

        if(c_m_stop < c_m_start)
        {
            hour = hour - 1;
            min = (c_m_stop +60) - c_m_start;
        }

        else
        {
            min = c_m_stop - c_m_start;
        }
        all_hour +=hour;
        all_minute+=min;
    }

    /**
     * Whe check length time the box in format hh:mm or hhmm
     * @param str - time enter the box
     */
    private void checkWidth( String str)
    {

        char[] c = str.toCharArray();


        if(str.length() == 5 && c[2]==':' && c[0]!=':' && c[1]!=':' && c[3]!=':' && c[4]!=':')
        {

                 c_hour = ((c[0] - 48)* 10)+ (c[1] - 48);
                 c_minute =((c[3] - 48)* 10) + (c[4] - 48);
                 check(c_hour,c_minute);
        }

        else {
            if (str.length() == 4 && str.matches("[0-9]+")) {
                c_hour = ((c[0] - 48) * 10) + (c[1] - 48);
                c_minute = ((c[2] - 48) * 10) + (c[3] - 48);
                check(c_hour, c_minute);
            }
            else {
                c_hour = 0;
                c_minute = 0;
                return;
            }

        }
    }

    /**
     * Number, who we enter the time box don`t be more hour >=24 or minute>=60
     * @param hour
     * @param minute
     */
    private void check(int hour, int minute)
    {
        if(hour>=24 || minute>=60)
        {
            c_hour=0;
            c_minute=0;
            return ;
        }


    }

    /**
     * Add current hour and minute to all hour and minute
     * @param hour
     * @param minute
     */
      private void getAlltime(int hour, int minute)
    {
        all_hour += hour;
        minute += 1;
        all_minute += minute;

        if( all_minute >= 60)
        {
            all_hour+=1;
            all_minute -= 60;
        }
    }

    /**
     * Count money
     * all time your work
     */
    private void getCash()
    {
        suma = (all_hour*money) + (all_minute*money/60);
    }

}
