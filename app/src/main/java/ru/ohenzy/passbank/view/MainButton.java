package ru.ohenzy.passbank.view;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.ohenzy.passbank.activity.MainActivity;
import ru.ohenzy.passbank.struct.AccountData;


public class MainButton extends LinearLayout {

    private final TextView text;
    private final LayoutParams params;
    private final MainActivity activity;

    public MainButton(MainActivity act, final AccountData account) {
        super(act.getApplicationContext());
        this.activity = act;
        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.leftMargin = 40;
        params.rightMargin = 40;
        text = new TextView(activity.getApplicationContext());
        text.setText(account.getName());
        text.setTextColor(Color.BLACK);
        text.setTextColor(Color.WHITE);
        text.setTextSize(18);
        this.setBackgroundColor(Color.GRAY);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainButton.this.setBackgroundColor(Color.LTGRAY);
                activity.swapActivity(account);
                activity.finish();
            }
        });
        this.addView(text, params);
    }

    public MainButton(MainActivity act, String name) {
        super(act.getApplicationContext());
        this.activity = act;
        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.leftMargin = 40;
        params.rightMargin = 40;
        text = new TextView(activity.getApplicationContext());
        text.setText(name);
        text.setTextColor(Color.BLACK);
        text.setTextColor(Color.WHITE);
        text.setTextSize(18);
        this.setBackgroundColor(Color.GRAY);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainButton.this.setBackgroundColor(Color.LTGRAY);
                activity.swapActivity(new AccountData("","",""));
                activity.finish();
            }
        });

        this.addView(text, params);
    }
}
