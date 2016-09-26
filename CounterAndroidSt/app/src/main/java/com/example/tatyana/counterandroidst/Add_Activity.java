package com.example.tatyana.counterandroidst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Tatyana on 2016-08-28.
 */

/**
 * This class contain add activity, where we can write in the box start work time and stop work time
 */
public class Add_Activity extends Activity {
    /**
     * Create add activity
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        final EditText start_work;
        final EditText stop_work;
        Button submit;

        start_work = (EditText) findViewById(R.id.edit_start_work);
        stop_work = (EditText) findViewById(R.id.edit_stop_work);
        submit = (Button) findViewById(R.id.button_submit_addlayout);
        submit.setOnClickListener(new View.OnClickListener() {
            /**
             * Submit time start and time stop and send to main activity, where count all time and all earn
             * @param view
             */
            public void onClick(View view) {

                Intent intent = new Intent(Add_Activity.this, MainActivity.class);
                intent.putExtra("start",start_work.getText().toString());
                intent.putExtra("stop",stop_work.getText().toString());
                setResult(RESULT_OK, intent);
                finish();

            }
        });

    }
}
