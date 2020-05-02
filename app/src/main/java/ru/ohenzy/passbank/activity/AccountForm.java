package ru.ohenzy.passbank.activity;


import android.content.Intent;
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
    private EditText name;
    private EditText login;
    private EditText password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.account_form);
        manager = new SerialManager(this.getApplicationContext());
        name = findViewById(R.id.name);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);

        name.setText(this.getIntent().getStringExtra("name"));
        login.setText(this.getIntent().getStringExtra("login"));
        password.setText(this.getIntent().getStringExtra("password"));

        buttonSave = findViewById(R.id.save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!name.getText().toString().isEmpty() & !login.getText().toString().isEmpty() & !password.getText().toString().isEmpty()){
                    manager.addAccount(new AccountData(name.getText().toString(), login.getText().toString(),password.getText().toString()));
                    Intent intent = new Intent(AccountForm.this, MainActivity.class);
                    AccountForm.this.startActivity(intent);
                    AccountForm.this.finish();
                }
            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(AccountForm.this, MainActivity.class);
        AccountForm.this.startActivity(intent);
        AccountForm.this.finish();
    }

}
