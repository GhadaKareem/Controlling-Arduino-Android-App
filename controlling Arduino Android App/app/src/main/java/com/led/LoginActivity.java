package com.led;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.led.led.R;

public class LoginActivity extends ActionBarActivity {
    Button Login;
    EditText USERNAME,USERPASS;
    String username,userpass;
    Context CTX = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login = (Button) findViewById(R.id.b_login);
        USERNAME = (EditText) findViewById(R.id.user_name);
        USERPASS = (EditText) findViewById(R.id.user_pass);
        Login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                username = USERNAME.getText().toString();
                userpass = USERPASS.getText().toString();
                DatabaseOperations DOP = new DatabaseOperations(CTX);
                Cursor CR = DOP.getInformation(DOP);
                CR.moveToFirst();
                boolean loginstatus = false;
                String NAME = "";

                do {
                    if (username.equals(CR.getString(0)) && (userpass.equals(CR.getString(1)))) {
                        loginstatus = true;
                        NAME = CR.getString(0);
                    }

                } while (CR.moveToNext());
                if (loginstatus) {
                    Toast.makeText(getBaseContext(), "Login Success----\n Welcome " + NAME, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this,correctlogin.class);
                    Bundle BN = new Bundle();
                    BN.putString("user_name",NAME );
                    BN.putString("user_pass",userpass );
                    i.putExtras(BN);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(getBaseContext(), "Login Failed---- ", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
    }
}
