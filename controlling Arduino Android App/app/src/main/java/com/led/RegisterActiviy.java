package com.led;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.led.led.R;



public class RegisterActiviy extends ActionBarActivity {
    EditText USER_NAME,USER_PASS,CON_PASS;
    String user_name,user_pass,con_pass;
    Button REG;
    Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activiy);
        USER_NAME = (EditText) findViewById(R.id.reg_user);
        USER_PASS = (EditText) findViewById(R.id.reg_pass) ;
        CON_PASS = (EditText) findViewById(R.id.con_pass);
        REG = (Button) findViewById(R.id.user_reg);
        REG.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                user_name = USER_NAME.getText().toString();
                user_pass = USER_PASS.getText().toString();
                con_pass = CON_PASS.getText().toString();


                if(!(user_pass.equals(con_pass)))
                {
                    Toast.makeText(getBaseContext(),"Passwords are not matching", Toast.LENGTH_LONG).show();
                    USER_NAME.setText("");
                    USER_PASS.setText("");
                    CON_PASS.setText("");
                }
                else
                {
                    DatabaseOperations DB = new DatabaseOperations(ctx);
                    DB.putInformation(DB, user_name, user_pass);
                    Toast.makeText(getBaseContext(), "Registration success", Toast.LENGTH_LONG).show();
                    finish();

                }

            }
        });
    }

}
