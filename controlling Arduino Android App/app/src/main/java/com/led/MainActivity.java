package com.led;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.led.led.DeviceList;
import com.led.led.R;
import com.led.led.ledControl;

public class MainActivity extends ActionBarActivity {
    Button Login, Register;
    int status = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login = (Button) findViewById(R.id.Login);
        Register = (Button) findViewById(R.id.Reg);

        Login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                status = 1;
                Bundle b = new Bundle();
                b.putInt("status", status);
                Intent i  = new Intent(MainActivity.this, LoginActivity.class);
                i.putExtras(b);
                startActivity(i);

            }
        });

        Register.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i  = new Intent(MainActivity.this,RegisterActiviy.class);
                startActivity(i);
            }
        });

    }


}

