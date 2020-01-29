package com.led;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.led.led.ledControl;
import com.led.led.DeviceList;
import com.led.led.R;

public class correctlogin extends ActionBarActivity {
    Button play, Delete, Update;
    String user_name,user_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correctlogin);
        Delete = (Button) findViewById(R.id.Delete);
        Update = (Button) findViewById(R.id.Update);
        play=(Button) findViewById(R.id.play);

        play.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Bundle BN = getIntent().getExtras();
                Intent i  = new Intent(correctlogin.this, DeviceList.class);
                startActivity(i);
                finish();
            }
        });
        Update.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Bundle BN = getIntent().getExtras();
                user_name = BN.getString("user_name");
                user_pass = BN.getString("user_pass");
                Intent i  = new Intent(correctlogin.this,UpdateActivity.class);
                Bundle BN1 = new Bundle();
                BN1.putString("user_name", user_name );
                BN1.putString("user_pass", user_pass );
                i.putExtras(BN1);
                startActivity(i);
                finish();
            }
        });
        Delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Bundle BN = getIntent().getExtras();
                user_name = BN.getString("user_name");
                user_pass = BN.getString("user_pass");
                Intent i  = new Intent(correctlogin.this,DeleteActvity.class);
                Bundle BN1 = new Bundle();
                BN1.putString("user_name", user_name );
                BN1.putString("user_pass", user_pass );
                i.putExtras(BN1);
                startActivity(i);
                finish();


            }
        });

    }
}
