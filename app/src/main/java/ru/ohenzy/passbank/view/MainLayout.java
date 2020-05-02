package ru.ohenzy.passbank.view;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.List;
import ru.ohenzy.passbank.activity.MainActivity;
import ru.ohenzy.passbank.struct.AccountData;


public class MainLayout extends LinearLayout {

    private final LinearLayout.LayoutParams params;
    private final MainActivity activity;


    @SuppressLint("ClickableViewAccessibility")
    public MainLayout(final MainActivity activ) {
        super(activ.getBaseContext());
        this.activity = activ;
        this.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        this.setOrientation(LinearLayout.VERTICAL);
        this.setBackgroundColor(Color.DKGRAY);
        params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 200);
        params.gravity = Gravity.CENTER;
        params.topMargin = 50;
        params.bottomMargin = 50;
        params.leftMargin = 40;
        params.rightMargin = 40;
    }

    public ScrollView scroll(){
        ScrollView scroll = new ScrollView(activity.getApplicationContext());
        scroll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        scroll.setBackgroundColor(Color.DKGRAY);
        scroll.addView(this);
        return  scroll;
    }

    public void addList(List<AccountData> accounts){
        if(accounts != null && !accounts.isEmpty())
            for (AccountData accountData : accounts)
                this.addView(new MainButton(activity, accountData), params);

        this.addView(new MainButton(activity,"+ Добавить"), params);
    }


}
