package ru.ohenzy.passbank.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.ohenzy.passbank.R;
import ru.ohenzy.passbank.struct.AccountData;
import ru.ohenzy.passbank.util.SerialManager;


public class AccountForm extends AppCompatActivity {

    private SerialManager manager;
    private Button buttonSave;
    private Button buttonDelete;
    private EditText name;
    private EditText login;
    private EditText password;
    private boolean enable = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setContentView(R.layout.account_form);
        manager = new SerialManager(this.getApplicationContext());
        name = findViewById(R.id.name);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);

        name.setText(this.getIntent().getStringExtra("name"));
        login.setText(this.getIntent().getStringExtra("login"));
        password.setText(this.getIntent().getStringExtra("password"));
        if(name.getText().toString().isEmpty() && login.getText().toString().isEmpty() && password.getText().toString().isEmpty())
            enable = true;
        this.setEnableField(enable);

        buttonSave = findViewById(R.id.save);
        if(!enable)
            buttonSave.setText("Изменить");
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!enable){
                    AccountForm.this.setEnableField(enable = true);
                    buttonSave.setText("Сохранить");
                } else {
                    if(!name.getText().toString().isEmpty() & !login.getText().toString().isEmpty() & !password.getText().toString().isEmpty()){
                        manager.addAccount(new AccountData(name.getText().toString(), login.getText().toString(),password.getText().toString()));
                        swapActivity();
                    }
                }
            }
        });
        buttonDelete = findViewById(R.id.delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AccountForm.this);
                builder.setMessage("ЧТО ПРЯМ СОВСЕМ??")
                        .setPositiveButton("УГУ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                manager.removeAccount(new AccountData(name.getText().toString(), login.getText().toString(),password.getText().toString()));
                                swapActivity();
                            }
                        })
                        .setNegativeButton("не", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.show();
            }
        });
    }

    private void setEnableField(boolean flag){
        name.setEnabled(flag);
        login.setEnabled(flag);
        password.setEnabled(flag);
    }

    @Override
    public void onBackPressed(){
        swapActivity();
    }

    private void swapActivity(){
        Intent intent = new Intent(AccountForm.this, MainActivity.class);
        AccountForm.this.startActivity(intent);
        AccountForm.this.finish();
    }

}