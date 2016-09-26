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
 * This class contain replace money activity, where we can change our earn per hour
 */
public class Replace_money_Activity extends Activity {
    /**
     * Create raplace money activity
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.replace_money_layout);

        final EditText cash_in;
        Button submit;

        cash_in = (EditText) findViewById(R.id.edit_box_money);
        submit = (Button) findViewById(R.id.button_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            /**
             * Submit our earn per hour and send to main activity
             * @param view
             */
            public void onClick(View view) {

                Intent intent = new Intent(Replace_money_Activity.this, MainActivity.class);
                intent.putExtra("cash",cash_in.getText().toString());
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }
}
