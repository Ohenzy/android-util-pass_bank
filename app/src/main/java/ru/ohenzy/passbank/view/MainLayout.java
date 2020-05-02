package ru.ohenzy.passbank.view;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
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
        params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 200);
        params.gravity = Gravity.CENTER;
        params.topMargin = 100;
        params.leftMargin = 40;
        params.rightMargin = 40;


    }

    public void addList(List<AccountData> accounts){
        if(accounts != null && !accounts.isEmpty())
            for (AccountData accountData : accounts)
                this.addView(new MainButton(activity, accountData), params);

        this.addView(new MainButton(activity,"+ Добавить"), params);
    }


}
