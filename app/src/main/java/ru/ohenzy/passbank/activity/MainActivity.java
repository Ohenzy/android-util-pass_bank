package ru.ohenzy.passbank.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ru.ohenzy.passbank.struct.AccountData;
import ru.ohenzy.passbank.view.MainLayout;
import ru.ohenzy.passbank.util.SerialManager;

public class MainActivity extends AppCompatActivity {

    private MainLayout layout;
    private SerialManager serialManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = new MainLayout(this);
        serialManager = new SerialManager(this.getApplicationContext());
        layout.setBackgroundColor(Color.DKGRAY);
        layout.addList(serialManager.getAccountData());

        this.setContentView(layout);
    }

    @Override
    protected void onStop() {
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  STOP  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" );
        serialManager.save();
        super.onStop();
    }

    public void swapActivity(AccountData account) {
        Intent intent = new Intent(MainActivity.this, AccountForm.class);
        if(account != null){
            intent.putExtra("name", !account.getName().isEmpty() ? account.getName() : "" );
            intent.putExtra("login", !account.getLogin().isEmpty() ? account.getLogin() : "" );
            intent.putExtra("password", !account.getPassword().isEmpty() ? account.getPassword() : "" );
        }
        this.startActivity(intent);
        this.onStop();
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }
}