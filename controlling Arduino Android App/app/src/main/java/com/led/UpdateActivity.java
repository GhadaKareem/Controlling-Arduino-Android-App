package com.led;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.led.led.R;

public class UpdateActivity extends ActionBarActivity {
    String user_name,user_pass,New_user_name,New_user_pass;
    Button b_update;
    EditText newuser;
    Context CTX = this;
    DatabaseOperations DOP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Bundle BN = getIntent().getExtras();
        user_name = BN.getString("user_name");
        user_pass = BN.getString("user_pass");
        b_update = (Button) findViewById(R.id.b_update);
        newuser = (EditText) findViewById(R.id.new_user_name);
        b_update.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                New_user_name = newuser.getText().toString();
                DOP = new DatabaseOperations(CTX);
                DOP.updateUserInfo(DOP, user_name, user_pass, New_user_name,New_user_pass);
                Toast.makeText(getBaseContext(), "Updation Success.....", Toast.LENGTH_LONG).show();
                finish();
            }
        });





    }

}
