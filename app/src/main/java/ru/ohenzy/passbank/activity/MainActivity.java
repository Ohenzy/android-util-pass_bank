package ru.ohenzy.passbank.activity;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ScrollView;

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
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        layout = new MainLayout(this);
        serialManager = new SerialManager(this.getApplicationContext());
        layout.addList(serialManager.getAccountData());
        this.setContentView(layout.scroll());
    }

    @Override
    protected void onStop() {
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


}